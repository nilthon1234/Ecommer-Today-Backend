package com.GrupoToday.service;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.MarcaDto;
import com.GrupoToday.models.Marca;

import java.util.List;

public interface MarcaService {
    public List<CategoriaDTO> buscarNombreMarca(String nombre);
    public List<CategoriaDTO> listMarcas();
    public Marca addMarca(MarcaDto marcaDto);
    public Marca updateMarca(Integer id, MarcaDto marcaDto);
    public void deleteMarca(Integer id);
}
