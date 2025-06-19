
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.Random;

// Sistema de Navegación
public class SistemaNavegacion implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1000 + new Random().nextInt(500)); // Simula procesamiento variable
        return "🛰️ Navegación: trayectoria corregida con éxito.";
    }
}