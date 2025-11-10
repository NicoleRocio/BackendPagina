package com.plataforma.plataforma.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente;
    private LocalDateTime fecha;

    // ✅ Nuevo campo estado
    private String estado = "En espera";

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetallePedido> detalles;

    public Pedido() {
        this.fecha = LocalDateTime.now();
        this.estado = "En espera"; // ✅ por si acaso
    }

    public Pedido(String cliente, List<DetallePedido> detalles) {
        this.cliente = cliente;
        this.fecha = LocalDateTime.now();
        this.estado = "En espera"; // ✅ por si acaso
        this.detalles = detalles;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
        if (detalles != null) {
            detalles.forEach(d -> d.setPedido(this));
        }
    }
}
