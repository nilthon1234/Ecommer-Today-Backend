package com.GrupoToday.impl;

import com.GrupoToday.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {
    //private  final Zapa
    @Override
    public String subirArchivo(String path, MultipartFile file) throws IOException {
        // Get the file name
        String nombreArchivo = file.getOriginalFilename();

        // Crear objeto de archivo para el directorio
        File directory = new File(path);

        // Create the directory if it doesn't exist
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                throw new IOException("No se pudo crear el directorio: " + path);
            }
        }

        // Construya la ruta completa del archivo
        Path destinoPath = Paths.get(path, nombreArchivo);

        // Copie el archivo al destino.
        Files.copy(file.getInputStream(), destinoPath);
        return nombreArchivo;

    }

    @Override
    public InputStream obtenerArchivoRecursos(String path, String nombreArchivo) throws FileNotFoundException {
        String destino = path + File.separator + nombreArchivo;
        return new FileInputStream(destino);
    }
}
