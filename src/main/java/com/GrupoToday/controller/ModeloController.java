package com.GrupoToday.controller;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.impl.ModeloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/modelo")
@CrossOrigin(origins = "http://localhost:4200")
public class ModeloController {
    @Autowired
    private ModeloServiceImpl modeloService;

    @GetMapping("/list-all")
    public List<CategoriaDTO> listarModelos() {
        return modeloService.listAllModelos();
    }
}
