package com.example.Sesion6reto1.Producto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;


import java.util.List;


@SpringBootApplication
public class Sesion6reto1Application {

    public static void main(String[] args) {
        SpringApplication.run(Sesion6reto1Application.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductoRepository repo) {
        return args -> {
            repo.save(new Producto(null, "Laptop Lenovo", "Alta gama", 12500));
            repo.save(new Producto(null, "Mouse Logitech", "Ergonómico", 350));
            repo.save(new Producto(null, "Teclado Mecánico", "RGB switches", 950));
            repo.save(new Producto(null, "Monitor", "24 pulgadas", 3200));

            System.out.println("📦 Productos con precio mayor a 500:");
            repo.findByPrecioGreaterThan(500).forEach(System.out::println);

            System.out.println("\n🔍 Productos que contienen 'lap':");
            repo.findByNombreContainingIgnoreCase("lap").forEach(System.out::println);

            System.out.println("\n🎯 Productos con precio entre 400 y 1000:");
            repo.findByPrecioBetween(400, 1000).forEach(System.out::println);

            System.out.println("\n📘 Productos cuyo nombre empieza con 'm':");
            repo.findByNombreStartingWithIgnoreCase("m").forEach(System.out::println);
        };
    }
}
