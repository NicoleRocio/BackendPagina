package com.plataforma.plataforma.controller;

import com.plataforma.plataforma.model.Pedido;
import com.plataforma.plataforma.repository.PedidoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // ✅ Listar todos los pedidos
    @GetMapping
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    // ✅ Crear un nuevo pedido
    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {
        pedido.getDetalles().forEach(detalle -> detalle.setPedido(pedido));
        return pedidoRepository.save(pedido);
    }

    // ✅ Obtener pedido por ID
    @GetMapping("/{id}")
    public Pedido obtenerPorId(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id " + id));
    }
}
