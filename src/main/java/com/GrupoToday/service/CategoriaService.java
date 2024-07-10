package com.GrupoToday.service;

import java.util.List;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.response.ListResponseDTO;
import com.GrupoToday.models.Categoria;
import com.GrupoToday.models.Zapatilla;

public interface CategoriaService {
	
	public List<CategoriaDTO> findZapatillaByCategoriaNombre(String nombreCategoria);

}
