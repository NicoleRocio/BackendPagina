package com.plataforma.plataforma.config;

import com.plataforma.plataforma.model.Producto;
import com.plataforma.plataforma.repository.ProductoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final ProductoRepository productoRepository;

    public DataLoader(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @PostConstruct
    public void init() {
        if (productoRepository.count() == 0) {
            productoRepository.save(new Producto(
                    "Laptop Lenovo ThinkPad",
                    "Core i5, 8GB RAM, SSD 256GB",
                    "Lima",
                    4
            ));
            productoRepository.save(new Producto(
                    "Monitor Samsung 24''",
                    "Full HD, HDMI/VGA",
                    "Lima",
                    10
            ));
            productoRepository.save(new Producto(
                    "Proyector Epson X05",
                    "Resolución XGA, 3300 lúmenes",
                    "Lima",
                    0
            ));
            productoRepository.save(new Producto(
                    "Impresora HP LaserJet",
                    "Tóner negro, conexión Wi-Fi",
                    "Lima",
                    5
            ));
        }
    }
}
