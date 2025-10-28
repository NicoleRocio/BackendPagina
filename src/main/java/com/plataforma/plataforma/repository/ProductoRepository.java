package com.plataforma.plataforma.repository;

import com.plataforma.plataforma.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findBySede(String sede);
}
