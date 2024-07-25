package com.GrupoToday.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.impl.mapper.CategoriaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GrupoToday.models.Categoria;
import com.GrupoToday.repository.CategoriaRepository;
import com.GrupoToday.service.CategoriaService;

@Service
public class CategoriaImpl implements CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CategoriaMapper categoriaMapper;


	@Override
	public List<CategoriaDTO> findZapatillaByCategoriaNombre(String nombreCategoria) {
		Categoria categoria = categoriaRepository.findByNombre(nombreCategoria)
				.orElseThrow(() -> new RuntimeException("Categoria No Encontrada: " + nombreCategoria));
		return categoria.getZapatilla().stream()
				.map(zapatilla -> categoriaMapper.searchByCategoryName(
				categoria,zapatilla))
				.collect(Collectors.toList());
	}

	@Override
	public List<CategoriaDTO> listarTodo() {
		List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
				.map(categoria -> categoriaMapper
						.categoryFrom(categoria))
				.collect(Collectors.toList());

	}


}
