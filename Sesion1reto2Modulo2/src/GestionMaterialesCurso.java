
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;


public class GestionMaterialesCurso {

    // Método genérico para mostrar cualquier tipo de material
    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        System.out.println("\n📚 Materiales del curso:");
        for (MaterialCurso material : lista) {
            material.mostrarDetalle();
        }
    }

    // Método para contar duración total de videos
    public static void contarDuracionVideos(List<? extends Video> lista) {
        int total = 0;
        for (Video video : lista) {
            total += video.getDuracion();
        }
        System.out.println("\n🎥 Duración total de videos: " + total + " minutos");
    }

    // Método para marcar ejercicios como revisados
    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        System.out.println();
        for (Object item : lista) {
            if (item instanceof Ejercicio) {
                Ejercicio ejercicio = (Ejercicio) item;
                ejercicio.marcarRevisado();
            }
        }
    }

    // Método adicional para filtrar por autor
    public static void filtrarPorAutor(List<? extends MaterialCurso> lista, String autor) {
        System.out.println("\n🔍 Filtrando materiales por autor '" + autor + "':");
        Predicate<MaterialCurso> porAutor = material -> material.autor.equals(autor);

        lista.stream()
                .filter(porAutor)
                .forEach(MaterialCurso::mostrarDetalle);
    }

    public static void main(String[] args) {
        // Crear materiales de ejemplo
        List<MaterialCurso> materiales = new ArrayList<>();
        materiales.add(new Video("Introducción a Java", "Mario", 15));
        materiales.add(new Video("POO en Java", "Carlos", 20));
        materiales.add(new Articulo("Historia de Java", "Ana", 1200));
        materiales.add(new Articulo("Tipos de datos", "Luis", 800));
        materiales.add(new Ejercicio("Variables y tipos", "Luis", false));
        materiales.add(new Ejercicio("Condicionales", "Mario", false));

        // Mostrar todos los materiales
        mostrarMateriales(materiales);

        // Crear lista solo de videos para contar duración
        List<Video> videos = new ArrayList<>();
        for (MaterialCurso m : materiales) {
            if (m instanceof Video) {
                videos.add((Video) m);
            }
        }
        contarDuracionVideos(videos);

        // Marcar ejercicios como revisados
        marcarEjerciciosRevisados(materiales);

        // Filtrar por autor (desafío adicional)
        filtrarPorAutor(materiales, "Mario");
    }
}