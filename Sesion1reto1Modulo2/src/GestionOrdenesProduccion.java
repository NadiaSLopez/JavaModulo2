import java.util.ArrayList;
import java.util.List;


public class GestionOrdenesProduccion {

    // Método genérico para mostrar cualquier tipo de orden
    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        System.out.println("\n📋 Órdenes registradas:");
        for (OrdenProduccion orden : lista) {
            orden.mostrarResumen();
        }
    }

    // Método para procesar órdenes personalizadas
    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        System.out.println("\n💰 Procesando órdenes personalizadas...");
        for (Object item : lista) {
            if (item instanceof OrdenPersonalizada) {
                OrdenPersonalizada orden = (OrdenPersonalizada) item;
                orden.agregarCostoAdicional(costoAdicional);
            }
        }
    }

    // Método opcional para contar órdenes por tipo
    public static void contarOrdenes(List<? extends OrdenProduccion>... listas) {
        System.out.println("\n📊 Resumen total de órdenes:");
        int masa = 0, personalizadas = 0, prototipos = 0;

        for (List<? extends OrdenProduccion> lista : listas) {
            for (OrdenProduccion orden : lista) {
                if (orden instanceof OrdenMasa) masa++;
                else if (orden instanceof OrdenPersonalizada) personalizadas++;
                else if (orden instanceof OrdenPrototipo) prototipos++;
            }
        }

        System.out.println("🔧 Producción en masa: " + masa);
        System.out.println("🛠️ Personalizadas: " + personalizadas);
        System.out.println("🧪 Prototipos: " + prototipos);
    }

    public static void main(String[] args) {
        // Crear listas de diferentes tipos de órdenes
        List<OrdenMasa> ordenesMasa = new ArrayList<>();
        ordenesMasa.add(new OrdenMasa("A123", 500));
        ordenesMasa.add(new OrdenMasa("A124", 750));

        List<OrdenPersonalizada> ordenesPersonalizadas = new ArrayList<>();
        ordenesPersonalizadas.add(new OrdenPersonalizada("P456", 100, "ClienteX"));
        ordenesPersonalizadas.add(new OrdenPersonalizada("P789", 150, "ClienteY"));

        List<OrdenPrototipo> ordenesPrototipo = new ArrayList<>();
        ordenesPrototipo.add(new OrdenPrototipo("T789", 10, "Diseño"));
        ordenesPrototipo.add(new OrdenPrototipo("T790", 5, "Pruebas"));

        // Mostrar todas las órdenes
        mostrarOrdenes(ordenesMasa);
        mostrarOrdenes(ordenesPersonalizadas);
        mostrarOrdenes(ordenesPrototipo);

        // Procesar órdenes personalizadas
        procesarPersonalizadas(ordenesPersonalizadas, 200);

        // Contar órdenes (desafío adicional)
        contarOrdenes(ordenesMasa, ordenesPersonalizadas, ordenesPrototipo);
    }
}
