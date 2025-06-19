package com.example.Sesion5reto2;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

@SpringBootApplication
public class UciMonitorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(UciMonitorApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("📡 Iniciando monitoreo de pacientes críticos en UCI...\n");

        Flux.merge(
                generarFlujoPaciente(1),
                generarFlujoPaciente(2),
                generarFlujoPaciente(3)
        )
        .delayElements(Duration.ofSeconds(1)) // Simula backpressure
        .subscribe(alerta -> System.out.println("⚠️ " + alerta));
    }

    private Flux<String> generarFlujoPaciente(int idPaciente) {
        Random random = new Random();

        return Flux.interval(Duration.ofMillis(300))
                 .flatMap(ignored -> {
    int fc = random.nextInt(81) + 40;
    int paSist = random.nextInt(81) + 80;
    int paDiast = random.nextInt(41) + 50;
    int spo2 = random.nextInt(21) + 80;

    String mensaje = null;

    if (fc < 50 || fc > 120) {
        mensaje = "Paciente " + idPaciente + " - FC crítica: " + fc + " bpm";
    } else if (paSist < 90 || paDiast < 60 || paSist > 140 || paDiast > 90) {
        mensaje = "Paciente " + idPaciente + " - PA crítica: " + paSist + "/" + paDiast + " mmHg";
    } else if (spo2 < 90) {
        mensaje = "Paciente " + idPaciente + " - SpO2 baja: " + spo2 + "%";
    }

    return mensaje != null ? Flux.just(mensaje) : Flux.empty();
});

     }
}
