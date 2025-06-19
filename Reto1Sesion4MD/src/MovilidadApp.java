import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class MovilidadApp {

    // Simula calcular la ruta (2-3 segundos de latencia)
    public CompletableFuture<String> calcularRuta() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("🚦 Calculando ruta...");
                TimeUnit.SECONDS.sleep(2 + new Random().nextInt(2)); // 2-3 segundos
                // Simulación de posible error (descomenta para probar manejo de error)
                // if (new Random().nextBoolean()) throw new RuntimeException("Fallo al calcular ruta");
                return "Centro -> Norte";
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al calcular la ruta", e);
            }
        });
    }

    // Simula estimar la tarifa (1-2 segundos de latencia)
    public CompletableFuture<Double> estimarTarifa() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("💰 Estimando tarifa...");
                TimeUnit.SECONDS.sleep(1 + new Random().nextInt(2)); // 1-2 segundos
                // Simulación de posible error (descomenta para probar manejo de error)
                // if (new Random().nextBoolean()) throw new RuntimeException("Fallo al estimar tarifa");
                return 75.50;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al estimar la tarifa", e);
            }
        });
    }

    // Combina ambas tareas y muestra el resultado final
    public void procesarSolicitud() {
        calcularRuta()
                .thenCombine(estimarTarifa(), (ruta, tarifa) ->
                        "✅ 🚗 Ruta calculada: " + ruta + " | Tarifa estimada: $" + tarifa
                )
                .exceptionally(ex -> "❌ Error en el proceso: " + ex.getMessage())
                .thenAccept(System.out::println);
    }
}
