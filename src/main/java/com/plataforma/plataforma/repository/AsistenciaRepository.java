package com.plataforma.plataforma.repository;

import com.plataforma.plataforma.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByEmpleadoId(Long empleadoId);
    List<Asistencia> findByEmpleadoIdAndFecha(Long empleadoId, LocalDate fecha);
}
