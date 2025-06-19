
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.Random;


// Clase principal para ejecutar la simulación
class MisionEspacial {
    public static void main(String[] args) {
        System.out.println("🚀 Simulación de misión espacial iniciada...");

        // Parte 2: Ejecutar tareas con ExecutorService
        // Usar Executors.newFixedThreadPool(4) para un pool de 4 hilos
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Enviar las tareas con submit()
        Future<String> navResult = executor.submit(new SistemaNavegacion());
        Future<String> svResult = executor.submit(new SistemaSoporteVital());
        Future<String> ctResult = executor.submit(new SistemaControlTermico());
        Future<String> commResult = executor.submit(new SistemaComunicaciones());

        // Parte 3: Mostrar los resultados al finalizar
        try {
            System.out.println(commResult.get()); // Orden de salida puede variar
            System.out.println(svResult.get());
            System.out.println(ctResult.get());
            System.out.println(navResult.get());

            System.out.println("✅ Todos los sistemas reportan estado operativo.");

        } catch (Exception e) {
            System.err.println("Error durante la simulación: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Asegurarse de cerrar el ejecutor con shutdown()
            executor.shutdown();
            try {
                // Esperar a que todas las tareas terminen o que pase un tiempo máximo
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    executor.shutdownNow(); // Forzar el cierre si no terminan
                }
            } catch (InterruptedException ie) {
                executor.shutdownNow();
                Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
            }
        }
    }
}