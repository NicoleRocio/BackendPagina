package com.plataforma.plataforma.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileStorageService {

    private final String UPLOAD_DIR = "src/main/resources/static/uploads/imagenes/";

    public String guardarImagen(MultipartFile archivo) {

        if (archivo.isEmpty()) {
            return null;
        }

        try {
            // Nombre único para evitar colisiones
            String nombreArchivo = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();

            Path ruta = Paths.get(UPLOAD_DIR + nombreArchivo);

            Files.copy(archivo.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

            return nombreArchivo;

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar imagen: " + e.getMessage());
        }
    }
}
