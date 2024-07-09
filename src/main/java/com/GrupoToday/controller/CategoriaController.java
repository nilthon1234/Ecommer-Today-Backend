package com.GrupoToday.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.GrupoToday.models.Zapatilla;
import com.GrupoToday.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/buscar")
	public List<Zapatilla> listarPorNombreCategoria(@RequestParam String categoriaNombre) {
		return categoriaService.findZapatillaByCategoriaNombre(categoriaNombre);
	}	

}
