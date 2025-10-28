package com.plataforma.plataforma.repository;

import com.plataforma.plataforma.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
