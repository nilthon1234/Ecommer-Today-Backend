	package com.GrupoToday.controller;

	import java.util.List;

	import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.*;

	import com.GrupoToday.models.Zapatilla;
	import com.GrupoToday.service.CategoriaService;

	@RestController
	@RequestMapping("/categoria")
	@CrossOrigin(origins = "http://localhost:4200")
	public class CategoriaController {

		@Autowired
		private CategoriaService categoriaService;

		@GetMapping("/buscar")
		public List<CategoriaDTO> listarPorNombreCategoria(@RequestParam String categoriaNombre) {
			return categoriaService.findZapatillaByCategoriaNombre(categoriaNombre);
		}

	}
