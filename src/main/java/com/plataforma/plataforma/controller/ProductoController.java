package com.plataforma.plataforma.controller;
import com.plataforma.plataforma.model.Producto;
import com.plataforma.plataforma.repository.ProductoRepository;
import com.plataforma.plataforma.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoRepository productoRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // ✅ Subir imagen
    @PostMapping("/upload")
    public String subirImagen(@RequestParam("imagen") MultipartFile file) {
        return fileStorageService.guardarImagen(file);
    }

    @GetMapping("/test")
    public String test() {
        return "OK";
    }

    // ✅ Crear producto con imagen
    @PostMapping("/crear")
    public Producto crearProducto(
            @RequestPart("producto") Producto producto,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {

        if (imagen != null && !imagen.isEmpty()) {
            String nombreImagen = fileStorageService.guardarImagen(imagen);
            producto.setImagen(nombreImagen);
        }
        return productoRepository.save(producto);
    }

    // ✅ Actualizar producto
    @PutMapping("/{id}")
    public Producto actualizarProducto(
            @PathVariable Long id,
            @RequestPart("producto") Producto productoActualizado,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {

        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setSede(productoActualizado.getSede());
            producto.setStock(productoActualizado.getStock());

            if (imagen != null && !imagen.isEmpty()) {
                String nombreImagen = fileStorageService.guardarImagen(imagen);
                producto.setImagen(nombreImagen);
            }

            return productoRepository.save(producto);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));
    }

    // ✅ Listar productos
    @GetMapping
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    // ✅ Buscar por ID
    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));
    }

    // ✅ Eliminar
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }

    // ✅ Buscar por sede
    @GetMapping("/sede/{sede}")
    public List<Producto> buscarPorSede(@PathVariable String sede) {
        return productoRepository.findBySede(sede);
    }

    // ✅ Reducir stock (CORREGIDO)
    @PutMapping("/reducir-stock/{id}/{cantidad}")
    public Producto reducirStock(
            @PathVariable Long id,
            @PathVariable int cantidad) {

        return productoRepository.findById(id).map(producto -> {

            if (producto.getStock() < cantidad) {
                throw new RuntimeException("Stock insuficiente");
            }

            producto.setStock(producto.getStock() - cantidad);

            return productoRepository.save(producto);

        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
}
