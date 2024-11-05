	package com.GrupoToday.controller;

	import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.GrupoToday.DTO.modelsDto.CateDTO;
import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

	import com.GrupoToday.service.CategoriaService;

	@RestController
	@RequestMapping("/admin/categoria")
	@CrossOrigin(origins = "http://localhost:4200")
	public class CategoriaController {

		
		private final CategoriaService categoriaService;
		
		public CategoriaController( CategoriaService categoriaService) {
			this.categoriaService = categoriaService;
		}
		
		
		@GetMapping("/list-all")
		public List<CategoriaDTO> lsCategorias(){
			return categoriaService.listarTodo();
		}
		@GetMapping("/buscar")
		public List<CategoriaDTO> listarPorNombreCategoria(@RequestParam String categoriaNombre) {
			return categoriaService.findZapatillaByCategoriaNombre(categoriaNombre);
		}
		
		@PostMapping("/add")
		public ResponseEntity <Map<String, String >> agregarCategoria(@RequestBody CateDTO categoriaDTO){
			Map<String, String> responseMap = new HashMap<>();
			try {
				categoriaService.addCategoria(categoriaDTO);
				responseMap.put("message", "se registro Correctamente");
				return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
			}catch (Exception e){
				responseMap.put("message", "Error al agregar");
				return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
				
			}
		}
		@PutMapping("/update/{id}")
		public ResponseEntity<Map<String, String>> update(@PathVariable Integer id, @RequestBody CateDTO cateDTO){
		
				Map<String, String> responseMap = new HashMap<>();
				if (id != null){
					categoriaService.update(id, cateDTO);
					responseMap.put("message", "actualizado");
					return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
				}else {
					responseMap.put("message", "ERROR AL ACTUALIZAR");
					return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
				}
				
			
		}
		@DeleteMapping("/delete/{id}")
//		
		public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id){
			Map<String, String>responseMap = new HashMap<>();
			
				categoriaService.deleteCategoria(id);
				
				if(responseMap.containsKey("message")){
					responseMap.put("message", "categoria no encontrado");
					return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
				}else {
					responseMap.put("message", "categoria Eliminado");
					return new ResponseEntity<>(responseMap, HttpStatus.OK);
				}
				
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
