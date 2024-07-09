package com.GrupoToday.service;

import java.util.List;

import com.GrupoToday.models.Zapatilla;

public interface CategoriaService {
	
	public List<Zapatilla> findZapatillaByCategoriaNombre(String nombreCategoria);

}
