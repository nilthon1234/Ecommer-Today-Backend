package com.GrupoToday.controller;

import com.GrupoToday.DTO.modelsDto.DepartamentoDto;
import com.GrupoToday.models.Departamento;
import com.GrupoToday.service.DepartamentoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departamento")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController( DepartamentoService departamentoService){
        this.departamentoService = departamentoService;
    }
    @GetMapping("/list-all")
    public List<DepartamentoDto> getAllDepartamento(){
        return departamentoService.getAllDepartamento();
    }
}
