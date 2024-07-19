package com.GrupoToday.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.response.ListResponseDTO;
import com.GrupoToday.impl.mapper.ZapatillaMapper;
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

	@Autowired
	private ZapatillaMapper zapatillaMapper;


	@Override
	public List<CategoriaDTO> findZapatillaByCategoriaNombre(String nombreCategoria) {
		Categoria categoria = categoriaRepository.findByNombre(nombreCategoria)
				.orElseThrow(() -> new RuntimeException("Categoria No Encontrada: " + nombreCategoria));
		return categoria.getZapatilla().stream()
				.map(zapatilla -> new CategoriaDTO(
						zapatilla.getId(),
						categoria.getNombre(),
						null,
						zapatilla.getNombre(),
						zapatilla.getMarca().getNombre(),
						zapatilla.getDescripcion(),
						zapatilla.getStock(),
						zapatilla.getPrecio(),
						null,
						null,
						null,
						null

				))
				.collect(Collectors.toList());
	}

	@Override
	public List<CategoriaDTO> listarTodo() {
		List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
				.map(categoria -> zapatillaMapper
						.categoryFrom(categoria.getNombre(),
										categoria.getId()))
				.collect(Collectors.toList());

	}


}
