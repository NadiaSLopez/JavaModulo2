package com.bedu.inventario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import jakarta.validation.ConstraintViolationException;

@SpringBootApplication
public class InventarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventarioApplication.class, args);
    }

    @Bean
    // Modificado para incluir CategoriaRepository y MarcaRepository
    public CommandLineRunner demo(ProductoRepository productoRepo, CategoriaRepository categoriaRepo, MarcaRepository marcaRepo) {
        return (args) -> {
            System.out.println("🚀 Iniciando demostración de inventario con categorías y marcas...");

            // --- Creación de Categorías (del ejemplo anterior) ---
            System.out.println("\n--- Creando categorías ---");
            Categoria tecnologia = new Categoria("Tecnología");
            categoriaRepo.save(tecnologia);
            Categoria electronica = new Categoria("Electrónica"); // Nueva categoría para mejor demostración
            categoriaRepo.save(electronica);
            Categoria hogar = new Categoria("Hogar");
            categoriaRepo.save(hogar);
            System.out.println("Categorías guardadas.");

            // --- Creación de Marcas (NUEVA PARTE) ---
            System.out.println("\n--- Creando marcas ---");
            Marca apple = new Marca("Apple");
            marcaRepo.save(apple);
            Marca samsung = new Marca("Samsung");
            marcaRepo.save(samsung);
            Marca logitech = new Marca("Logitech");
            marcaRepo.save(logitech);
            System.out.println("Marcas guardadas.");

            // --- Guardado de Productos con Categoría y Marca ---
            System.out.println("\n--- Guardando productos con categorías y marcas ---");
            productoRepo.save(new Producto("iPhone 15", "El último smartphone de Apple", 25000.00, tecnologia, apple));
            productoRepo.save(new Producto("iPad Pro", "Tablet potente para profesionales", 15000.00, tecnologia, apple));
            productoRepo.save(new Producto("Galaxy S23", "Smartphone Android de alta gama", 18000.00, tecnologia, samsung));
            productoRepo.save(new Producto("Smart TV QLED 4K", "Televisor inteligente de gran resolución", 10000.00, electronica, samsung));
            productoRepo.save(new Producto("Teclado Inalámbrico K380", "Teclado compacto para múltiples dispositivos", 950.00, tecnologia, logitech));
            productoRepo.save(new Producto("Mouse G502 HERO", "Mouse gamer de alta precisión", 600.00, tecnologia, logitech));
            productoRepo.save(new Producto("Batidora de mano", "Mezcladora potente para cocina", 800.00, hogar, null)); // Producto sin marca
            System.out.println("Productos guardados.");

            // --- Mostrar productos agrupados por marca (NUEVA PARTE) ---
            System.out.println("\n📚 Productos por marca:");
            marcaRepo.findAll().forEach(marca -> {
               System.out.println("🏷️ " + marca.getNombre() + ":");
               productoRepo.findAll().stream()
                  .filter(p -> p.getMarca() != null && p.getMarca().getId().equals(marca.getId()))
                  .forEach(p -> System.out.println("   - " + p.getNombre()));
            });

            // Mostrar productos sin categoría (del ejemplo anterior, ajustado)
            System.out.println("\n📂 Todos los Productos registrados (con categoría y marca):");
            productoRepo.findAll().forEach(System.out::println);


            // --- Demostración de Validaciones (del ejemplo anterior) ---
            System.out.println("\n--- Probando validaciones (del reto anterior) ---");

            // Intento de guardar productos inválidos para probar validaciones
            System.out.println("\n⚠️ Probando validaciones con productos inválidos...");

            // Producto con nombre en blanco
            try {
                productoRepo.save(new Producto("", "Una descripción", 100.00, tecnologia, apple));
            } catch (ConstraintViolationException e) {
                System.out.println("ERROR: No se pudo guardar el producto (nombre en blanco): " + e.getMessage());
            } catch (DataIntegrityViolationException e) {
                System.out.println("ERROR DB: No se pudo guardar el producto (nombre en blanco): " + e.getRootCause().getMessage());
            }

            // Producto con precio menor a 1
            try {
                productoRepo.save(new Producto("Producto barato", "Un producto muy económico", 0.50, tecnologia, samsung));
            } catch (ConstraintViolationException e) {
                System.out.println("ERROR: No se pudo guardar el producto (precio < 1): " + e.getMessage());
            } catch (DataIntegrityViolationException e) {
                System.out.println("ERROR DB: No se pudo guardar el producto (precio < 1): " + e.getRootCause().getMessage());
            }

            // --- Demostración de Consultas Personalizadas (del ejemplo anterior) ---
            System.out.println("\n--- Ejecutando consultas personalizadas (del reto anterior) ---");

            // Imprimir todos los productos con precio mayor a 5000
            System.out.println("\n📦 Productos con precio mayor a 5000:");
            productoRepo.findByPrecioGreaterThan(5000.00).forEach(System.out::println);

            // Imprimir todos los productos que contengan "lap" en su nombre (case-insensitive)
            System.out.println("\n🔍 Productos que contienen 'phone' (ignoring case):");
            productoRepo.findByNombreContainingIgnoreCase("phone").forEach(System.out::println);

            // Imprimir productos con precio entre 1000 y 10000
            System.out.println("\n🎯 Productos con precio entre 1000 y 10000:");
            productoRepo.findByPrecioBetween(1000.00, 10000.00).forEach(System.out::println);

            // Imprimir productos cuyo nombre comience con "g" o "G"
            System.out.println("\n📘 Productos cuyo nombre empieza con 'g':");
            productoRepo.findByNombreStartingWithIgnoreCase("g").forEach(System.out::println);

            System.out.println("\n✅ Demostración de inventario completada.");
        };
    }
}