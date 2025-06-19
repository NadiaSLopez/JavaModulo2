import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class AeropuertoControl {
    private Random random = new Random();

    // Simula latencia de 2-3 segundos y 80% de éxito
    public CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia();
            boolean resultado = random.nextInt(100) < 80;
            System.out.println("🛣️ Pista disponible: " + resultado);
            return resultado;
        });
    }

    // Simula latencia y 85% de éxito
    public CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia();
            boolean resultado = random.nextInt(100) < 85;
            System.out.println("🌦️ Clima favorable: " + resultado);
            return resultado;
        });
    }

    // Simula latencia y 90% de éxito
    public CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia();
            boolean resultado = random.nextInt(100) < 90;
            System.out.println("🚦 Tráfico aéreo despejado: " + resultado);
            return resultado;
        });
    }

    // Simula latencia y 95% de éxito
    public CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia();
            boolean resultado = random.nextInt(100) < 95;
            System.out.println("👷‍♂️ Personal disponible: " + resultado);
            return resultado;
        });
    }

    // Simulación de latencia (2-3 segundos)
    private void simularLatencia() {
        try {
            TimeUnit.SECONDS.sleep(2 + random.nextInt(2)); // 2 o 3 segundos
        } catch (InterruptedException e) {
            throw new RuntimeException("Error en simulación de latencia");
        }
    }

    // Ejecuta todas las verificaciones en paralelo
    public void autorizarAterrizaje() {
        System.out.println("🛫 Verificando condiciones para aterrizaje...\n");

        CompletableFuture<Boolean> pista = verificarPista();
        CompletableFuture<Boolean> clima = verificarClima();
        CompletableFuture<Boolean> trafico = verificarTraficoAereo();
        CompletableFuture<Boolean> personal = verificarPersonalTierra();

        CompletableFuture.allOf(pista, clima, trafico, personal)
                .thenApply(v -> {
                    // Esperamos los resultados y los combinamos
                    try {
                        boolean condicionesOk = pista.get() && clima.get() && trafico.get() && personal.get();
                        return condicionesOk;
                    } catch (Exception e) {
                        throw new RuntimeException("Error al combinar verificaciones", e);
                    }
                })
                .thenAccept(condicionesOk -> {
                    if (condicionesOk) {
                        System.out.println("\n🛬 Aterrizaje autorizado: todas las condiciones óptimas.");
                    } else {
                        System.out.println("\n🚫 Aterrizaje denegado: condiciones no óptimas.");
                    }
                })
                .exceptionally(ex -> {
                    System.out.println("\n❌ Error en la verificación: " + ex.getMessage());
                    return null;
                });
    }
}
