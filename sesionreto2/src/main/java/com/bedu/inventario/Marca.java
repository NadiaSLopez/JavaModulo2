package com.bedu.inventario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity // Marca esta clase como una entidad JPA
public class Marca {

    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autogenerado
    private Long id;

    @NotBlank(message = "El nombre de la marca no puede estar en blanco") // Validación para el nombre
    private String nombre;

    protected Marca() {} // Constructor por defecto requerido por JPA

    public Marca(String nombre) {
        this.nombre = nombre;
    }

    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }

    // Setters (opcionales)
    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return String.format("Marca[id=%d, nombre='%s']", id, nombre);
    }
}