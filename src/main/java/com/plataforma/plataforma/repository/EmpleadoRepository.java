package com.plataforma.plataforma.repository;

import com.plataforma.plataforma.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    // Puedes agregar métodos personalizados si lo necesitas, por ejemplo:
    // Optional<Empleado> findByNombre(String nombre);
}
