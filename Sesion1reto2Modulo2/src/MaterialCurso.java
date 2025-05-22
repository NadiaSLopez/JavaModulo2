import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

// Clase abstracta base para todos los materiales del curso
abstract class MaterialCurso {
    protected String titulo;
    protected String autor;

    public MaterialCurso(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public abstract void mostrarDetalle();
}