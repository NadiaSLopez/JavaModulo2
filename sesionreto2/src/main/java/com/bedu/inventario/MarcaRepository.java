package com.bedu.inventario;

import org.springframework.data.jpa.repository.JpaRepository;

// Esta interfaz extiende JpaRepository para gestionar operaciones CRUD sobre la entidad Marca.
// El primer tipo genérico es la entidad (Marca) y el segundo es el tipo de su clave primaria (Long).
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    // Spring Data JPA provee automáticamente métodos como save(), findById(), findAll(), delete()
}