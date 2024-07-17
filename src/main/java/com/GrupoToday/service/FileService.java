package com.GrupoToday.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    String subirArchivo(String path, MultipartFile file) throws IOException;
    InputStream obtenerArchivoRecursos(String path, String nombreArchivo) throws FileNotFoundException;

}
