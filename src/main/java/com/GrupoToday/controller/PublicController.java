package com.GrupoToday.controller;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.DepartamentoDto;
import com.GrupoToday.DTO.modelsDto.MarcaDto;
import com.GrupoToday.models.Departamento;
import com.GrupoToday.service.CategoriaService;
import com.GrupoToday.service.DepartamentoService;
import com.GrupoToday.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    private final CategoriaService categoriaService;

    private final MarcaService marcaService;
    private final DepartamentoService departamentoService;

    public PublicController(MarcaService marcaService,
                            CategoriaService categoriaService,
                            DepartamentoService departamentoService){
        this.marcaService = marcaService;
        this.categoriaService = categoriaService;
        this.departamentoService = departamentoService;
    }
    @GetMapping("buscarCategoria")
    public List<CategoriaDTO> listarPorNombre(@RequestParam String nombre){
        return categoriaService.findZapatillaByCategoriaNombre(nombre);
    }
    @GetMapping("buscarMarca")
    public  List<CategoriaDTO> ListarMarca(@RequestParam String nombre){
        return marcaService.buscarNombreMarca(nombre);
    }
    @GetMapping("list-depa")
    public List<DepartamentoDto> listAllDepa(){
        return departamentoService.getAllDepartamento();
    }

}
