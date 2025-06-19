package com.example.sesion5reto1;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class MeridianPrimeMonitoring {


    static Random random = new Random();


    public static void main(String[] args) throws InterruptedException {
        // Sensores de tráfico: Nivel de congestión (0-100%) cada 500ms
        Flux<String> trafficSensor = Flux.interval(Duration.ofMillis(500))
                .map(i -> random.nextInt(101)) // 0-100%
                .filter(congestion -> congestion > 70)
                .map(congestion -> "🚗 Alerta: Congestión del " + congestion + "% en Avenida Solar")
                .doOnNext(System.out::println)
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.parallel());


        // Contaminación del aire: Nivel de partículas PM2.5 (ug/m3) cada 600ms
        Flux<String> airPollution = Flux.interval(Duration.ofMillis(600))
                .map(i -> random.nextInt(101)) // 0-100 ug/m3
                .filter(pm25 -> pm25 > 50)
                .map(pm25 -> "🌫️ Alerta: Contaminación alta (PM2.5: " + pm25 + " ug/m3)")
                .doOnNext(System.out::println)
                .subscribeOn(Schedulers.parallel());


        // Accidentes viales: Evento con prioridad cada 800ms
        String[] priorities = {"Baja", "Media", "Alta"};
        Flux<String> trafficAccidents = Flux.interval(Duration.ofMillis(800))
                .map(i -> priorities[random.nextInt(priorities.length)])
                .filter(priority -> priority.equals("Alta"))
                .map(priority -> "🚑 Emergencia vial: Accidente con prioridad " + priority)
                .doOnNext(System.out::println)
                .subscribeOn(Schedulers.parallel());


        // Trenes maglev: Retraso en minutos (0-10) cada 700ms
        Flux<String> maglevTrains = Flux.interval(Duration.ofMillis(700))
                .map(i -> random.nextInt(11)) // 0-10 min
                .filter(delay -> delay > 5)
                .map(delay -> "🚝 Tren maglev con retraso crítico: " + delay + " minutos")
                .delayElements(Duration.ofMillis(200)) // simula backpressure
                .doOnNext(System.out::println)
                .subscribeOn(Schedulers.parallel());


        // Semáforos inteligentes: Estado (Verde, Amarillo, Rojo) cada 400ms
        String[] states = {"Verde", "Amarillo", "Rojo"};
        AtomicInteger redCount = new AtomicInteger(0);


        Flux<String> trafficLights = Flux.interval(Duration.ofMillis(400))
                .map(i -> states[random.nextInt(states.length)])
                .map(state -> {
                    if (state.equals("Rojo")) {
                        if (redCount.incrementAndGet() >= 3) {
                            redCount.set(0);
                            return "🚦 Semáforo en Rojo detectado 3 veces seguidas en cruce Norte";
                        }
                    } else {
                        redCount.set(0);
                    }
                    return null;
                })
                .filter(msg -> msg != null)
                .doOnNext(System.out::println)
                .subscribeOn(Schedulers.parallel());


        // Combinación de todos los eventos críticos
        Flux<String> merged = Flux.merge(
                trafficSensor,
                airPollution,
                trafficAccidents,
                maglevTrains,
                trafficLights
        );


        // Alerta global si hay >= 3 eventos críticos simultáneamente
        merged.buffer(Duration.ofSeconds(1))
                .filter(events -> events.size() >= 3)
                .map(events -> {
                    StringBuilder alert = new StringBuilder("🚨 Alerta global: Múltiples eventos críticos detectados en Meridian Prime\n");
                    events.forEach(event -> alert.append(" • ").append(event).append("\n"));
                    return alert.toString();
                })
                .doOnNext(System.out::println)
                .subscribe();


        // Mantiene el programa en ejecución
        Thread.sleep(30_000); // Corre por 30 segundos
    }
}


