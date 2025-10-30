package com.plataforma.plataforma.controller;

import com.plataforma.plataforma.model.Asistencia;
import com.plataforma.plataforma.repository.AsistenciaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaController {

    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaController(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    @GetMapping
    public List<Asistencia> getAll() {
        return asistenciaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Asistencia> getById(@PathVariable Long id) {
        return asistenciaRepository.findById(id);
    }

    @PostMapping
    public Asistencia create(@RequestBody Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    @PutMapping("/{id}")
    public Asistencia update(@PathVariable Long id, @RequestBody Asistencia asistencia) {
        asistencia.setId(id);
        return asistenciaRepository.save(asistencia);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        asistenciaRepository.deleteById(id);
    }
}
