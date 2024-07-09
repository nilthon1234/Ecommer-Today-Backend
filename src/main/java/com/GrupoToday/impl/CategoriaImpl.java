package com.GrupoToday.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GrupoToday.models.Categoria;
import com.GrupoToday.models.Zapatilla;
import com.GrupoToday.repository.CategoriaRepository;
import com.GrupoToday.service.CategoriaService;

@Service
public class CategoriaImpl implements CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public List<Zapatilla> findZapatillaByCategoriaNombre(String nombreCategoria) {
		Categoria categoria = categoriaRepository.findByNombre(nombreCategoria)
				.orElseThrow(() -> new RuntimeException("Categoria No Encontrada"));
		return categoria.getZapatilla();
	}

}
