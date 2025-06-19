import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.Random;

// Sistema de Soporte Vital
public class SistemaSoporteVital implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(800 + new Random().nextInt(400)); // Simula procesamiento variable
        return "🧪 Soporte vital: presión y oxígeno dentro de parámetros normales.";
    }
}