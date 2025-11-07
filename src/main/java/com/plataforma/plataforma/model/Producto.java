package com.plataforma.plataforma.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String sede;
    private int stock;
    // ✅ Nuevo campo para almacenar el nombre del archivo
    private String imagen;

    public Producto() {
    }

    public Producto(String nombre, String descripcion, String sede, int stock, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sede = sede;
        this.stock = stock;
        this.imagen = imagen;
    }

    // Getters y Setters
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
