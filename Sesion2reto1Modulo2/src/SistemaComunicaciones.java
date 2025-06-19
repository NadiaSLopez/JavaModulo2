import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.Random;

// Sistema de Comunicaciones
public class SistemaComunicaciones implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(700 + new Random().nextInt(300)); // Simula procesamiento variable
        return "📡 Comunicaciones: enlace con estación terrestre establecido.";
    }
}