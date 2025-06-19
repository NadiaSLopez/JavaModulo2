package com.bedu.inventario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar en blanco")
    private String descripcion;

    @Min(value = 1, message = "El precio debe ser al menos 1")
    private double precio;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne // Relación Many-to-One con Marca: Muchos productos pueden pertenecer a una misma marca
    @JoinColumn(name = "marca_id") // Nombre de la columna FK en la tabla 'producto'
    private Marca marca;

    protected Producto() {}

    // Constructor público actualizado para incluir Categoria y Marca
    public Producto(String nombre, String descripcion, double precio, Categoria categoria, Marca marca) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.marca = marca;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public Categoria getCategoria() { return categoria; }
    public Marca getMarca() { return marca; } // Nuevo getter para la marca

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public void setMarca(Marca marca) { this.marca = marca; } // Nuevo setter para la marca


    @Override
    public String toString() {
        return String.format("Producto[id=%d, nombre='%s', precio=%.2f, categoria='%s', marca='%s']",
                id, nombre, precio,
                categoria != null ? categoria.getNombre() : "Sin categoría",
                marca != null ? marca.getNombre() : "Sin marca");
    }
}