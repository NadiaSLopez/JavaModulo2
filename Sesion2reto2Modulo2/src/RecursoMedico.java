import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random; // Para simular tiempos de uso variables

// 1️⃣ Crear una clase RecursoMedico
class RecursoMedico {
    private String nombre;
    private final ReentrantLock lock = new ReentrantLock(); // El candado para controlar el acceso

    public RecursoMedico(String nombre) {
        this.nombre = nombre;
    }

    public void usar(String profesional) {
        // Intentar adquirir el candado
        System.out.println(profesional + " intentando acceder a " + nombre + "...");
        lock.lock(); // Bloquea el recurso
        try {
            System.out.println("👩‍⚕️ " + profesional + " ha ingresado a " + nombre);

            // Simular tiempo de uso del recurso
            int tiempoUso = 1000 + new Random().nextInt(1500); // Entre 1 y 2.5 segundos
            Thread.sleep(tiempoUso);

            System.out.println("✅ " + profesional + " ha salido de " + nombre);
        } catch (InterruptedException e) {
            System.out.println("⚠️ " + profesional + " fue interrumpido mientras usaba " + nombre);
            Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
        } finally {
            lock.unlock(); // Siempre libera el candado, incluso si hay una excepción
        }
    }
}
