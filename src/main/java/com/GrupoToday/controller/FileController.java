package com.GrupoToday.controller;

import com.GrupoToday.service.FileService;
import com.sun.net.httpserver.HttpServer;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file/")
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    @Value("${proyect.poster}")
    private String path;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFileHandler(@RequestParam MultipartFile file ) throws IOException {
        String nombreArchivoSubido = fileService.subirArchivo(path, file);
        return ResponseEntity.ok("Archivo subido :" + nombreArchivoSubido);
    }

    @GetMapping( value = "/{nombreArchivo}")
    public void serveFileHandler(@PathVariable String nombreArchivo, HttpServletResponse response) throws IOException {
        InputStream resourceFile = fileService.obtenerArchivoRecursos(path,nombreArchivo);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(resourceFile,response.getOutputStream());
    }
}
