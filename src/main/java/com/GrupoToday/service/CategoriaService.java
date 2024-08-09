package com.GrupoToday.service;

import java.util.List;

import com.GrupoToday.DTO.modelsDto.CateDTO;
import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.models.Categoria;

public interface CategoriaService {
	
	public List<CategoriaDTO> findZapatillaByCategoriaNombre(String nombreCategoria);
	public List<CategoriaDTO> listarTodo();
	public Categoria addCategoria(CateDTO categoriaDTO);
	public Categoria update(Integer id, CateDTO categoriaDTO);
	void deleteCategoria(Integer id);  

}
