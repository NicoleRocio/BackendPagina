package com.plataforma.plataforma.controller;

import com.plataforma.plataforma.model.Producto;
import com.plataforma.plataforma.repository.ProductoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") // Permite llamadas desde cualquier frontend
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // ✅ Listar todos los productos
    @GetMapping
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    // ✅ Obtener producto por ID
    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));
    }

    // ✅ Crear un nuevo producto
    @PostMapping
    public Producto guardar(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // ✅ Actualizar un producto existente
    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setSede(productoActualizado.getSede());
            producto.setStock(productoActualizado.getStock());
            return productoRepository.save(producto);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));
    }

    // ✅ Eliminar un producto
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }

    // ✅ Buscar productos por sede
    @GetMapping("/sede/{sede}")
    public List<Producto> buscarPorSede(@PathVariable String sede) {
        return productoRepository.findBySede(sede);
    }
}
