package com.meridian;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


import java.time.Duration;
import java.util.Random;

public class MeridianApp {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        // 🚗 Sensores de tráfico
        Flux.interval(Duration.ofMillis(500))
                .map(i -> random.nextInt(101)) // Congestión 0–100%
                .filter(congestion -> congestion > 70)
                .onBackpressureBuffer() // Simula backpressure
                .subscribe(congestion ->
                        System.out.println("🚗 Alerta: Congestión del " + congestion + "% detectada")
                );

        // 🌫️ Contaminación del aire
        Flux.interval(Duration.ofMillis(600))
                .map(i -> random.nextInt(101)) // PM2.5 0–100 ug/m3
                .filter(pm -> pm > 50)
                .subscribe(pm ->
                        System.out.println("🌫️ Alerta: Contaminación alta (PM2.5: " + pm + " ug/m3)")
                );

        // 🚑 Accidentes viales
        String[] prioridades = {"Baja", "Media", "Alta"};
        Flux.interval(Duration.ofMillis(800))
                .map(i -> prioridades[random.nextInt(prioridades.length)])
                .filter(p -> p.equals("Alta"))
                .subscribe(p ->
                        System.out.println("🚑 Emergencia vial: Accidente con prioridad " + p)
                );

        // 🚝 Trenes maglev
        Flux.interval(Duration.ofMillis(700))
                .map(i -> random.nextInt(11)) // Retraso en minutos
                .filter(delay -> delay > 5)
                .onBackpressureBuffer()
                .subscribe(delay ->
                        System.out.println("🚝 Tren maglev con retraso crítico: " + delay + " minutos")
                );

        // 🚦 Semáforos inteligentes
        Flux.interval(Duration.ofMillis(400))
                .map(i -> {
                    String[] estados = {"Verde", "Amarillo", "Rojo"};
                    return estados[random.nextInt(estados.length)];
                })
                .buffer(3, 1) // Agrupa cada 3 estados seguidos
                .filter(estados -> estados.stream().allMatch(e -> e.equals("Rojo")))
                .subscribe(e ->
                        System.out.println("🚦 Semáforo en Rojo detectado 3 veces seguidas en cruce Norte")
                );

        // Esperar que los flujos sigan activos
        Thread.sleep(10000); // o usa .blockLast() si combinas con merge()
    }
}
