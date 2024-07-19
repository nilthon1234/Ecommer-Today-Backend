	package com.GrupoToday.controller;

	import java.util.List;

	import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
	import com.GrupoToday.models.Categoria;
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
		@GetMapping("/list-all")
		public List<CategoriaDTO> lsCategorias(){
			return categoriaService.listarTodo();
		}
		@GetMapping("/buscar")
		public List<CategoriaDTO> listarPorNombreCategoria(@RequestParam String categoriaNombre) {
			return categoriaService.findZapatillaByCategoriaNombre(categoriaNombre);
		}

	}
