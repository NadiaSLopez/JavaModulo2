
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.Random;
// Sistema de Control Térmico
public class SistemaControlTermico implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1200 + new Random().nextInt(600)); // Simula procesamiento variable
        return "🔥 Control térmico: temperatura estable (22°C).";
    }
}
