package com.plataforma.plataforma.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre; // DOCENTE, ADMINISTRATIVO, etc.

    // Relación inversa con Empleado
    @ManyToMany(mappedBy = "roles")
    private Set<Empleado> empleados;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Set<Empleado> getEmpleados() { return empleados; }
    public void setEmpleados(Set<Empleado> empleados) { this.empleados = empleados; }
}
