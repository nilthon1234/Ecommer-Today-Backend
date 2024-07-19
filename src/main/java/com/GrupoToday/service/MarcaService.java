package com.GrupoToday.service;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.models.Marca;

import java.util.List;

public interface MarcaService {
    public List<CategoriaDTO> buscarNombreMarca(String nombre);
    public List<CategoriaDTO> listMarcas();
}
