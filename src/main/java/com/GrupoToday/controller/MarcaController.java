package com.GrupoToday.controller;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marca")
@CrossOrigin(origins = "http://localhost:4200")
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    @GetMapping("/buscar")
    public List<CategoriaDTO> buscarMarca(@RequestParam String nombre) {
        return marcaService.buscarNombreMarca(nombre);
    }
}
