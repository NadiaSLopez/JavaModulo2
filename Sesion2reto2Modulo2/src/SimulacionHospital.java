
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

// 2️⃣ Crear tareas que representen a profesionales médicos (usando Lambdas como Runnable)
public class SimulacionHospital {
    public static void main(String[] args) {
        System.out.println("🏥 Iniciando acceso a la Sala de cirugía...");

        // Crear una instancia del RecursoMedico
        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        // 3️⃣ Ejecutar la simulación
        // Usar un ExecutorService con al menos 4 hilos para simular concurrencia
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Nombres de profesionales
        String[] profesionales = {"Dra. Sánchez", "Dr. Gómez", "Enf. Pérez", "Dra. López", "Dr. Ruiz", "Enf. García"};

        // Enviar múltiples tareas para que los profesionales intenten usar el recurso
        for (String profesional : profesionales) {
            executor.submit(() -> {
                salaCirugia.usar(profesional);
            });
        }

        // Apagar el ExecutorService y esperar a que todas las tareas terminen
        executor.shutdown(); // No acepta nuevas tareas, pero permite que las existentes terminen
        try {
            // Esperar un tiempo máximo para que todas las tareas completen
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Si no terminan en el tiempo, forzar el cierre
                System.err.println("Advertencia: Algunas tareas no terminaron a tiempo y fueron forzadas a cerrar.");
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            System.err.println("La espera fue interrumpida.");
            Thread.currentThread().interrupt();
        }

        System.out.println("\n--- Simulación finalizada ---");
    }
}