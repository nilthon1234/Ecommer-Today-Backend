package com.GrupoToday.controller;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.ModeloDto;
import com.GrupoToday.impl.ModeloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/modelo")
@CrossOrigin(origins = "http://localhost:4200")
public class ModeloController {
	@Autowired
	private ModeloServiceImpl modeloService;

	@GetMapping("/list-all")
	public List<CategoriaDTO> listarModelos() {
		return modeloService.listAllModelos();
	}

	@PostMapping("/add")
	public ResponseEntity<Map<String, String>> agregar(@RequestBody ModeloDto modeloDto) {
		Map<String, String> responseMap = new HashMap<>();
		try {
			modeloService.add(modeloDto);
			responseMap.put("message", "modelo Agregado");
			return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
		} catch (Exception e) {
			responseMap.put("message", "error no se actualizo");
			return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Map<String, String>> actualizacion(@PathVariable Integer id,
			@RequestBody ModeloDto modeloDto) {

		Map<String, String> respoMap = new HashMap<>();
		if (id != null) {
			modeloService.update(id, modeloDto);
			respoMap.put("message", "Modelo Actualizado Correctamente");
			return new ResponseEntity<>(respoMap, HttpStatus.CREATED);
		} else {
			respoMap.put("message", "error de actulizacion");
			return new ResponseEntity<>(respoMap, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, String>> deleteModelo(@PathVariable Integer id) {
		Map<String, String> response = new HashMap<>();

		modeloService.delete(id);
		if (response.containsKey("message")) {
			response.put("message", "error al eliminar modelo");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} else {
			response.put("message", "modelo eliminado Correctamente");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

	}
}
