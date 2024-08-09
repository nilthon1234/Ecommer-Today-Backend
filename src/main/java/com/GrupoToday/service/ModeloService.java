package com.GrupoToday.service;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.ModeloDto;
import com.GrupoToday.models.Modelo;

import java.util.List;

public interface ModeloService {
    public List<CategoriaDTO> listAllModelos();
    public Modelo add(ModeloDto modeloDto);
    public Modelo update(Integer id, ModeloDto modeloDto );
    public void delete(Integer id); 
}
