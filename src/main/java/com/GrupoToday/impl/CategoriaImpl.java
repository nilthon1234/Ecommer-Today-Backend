package com.GrupoToday.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.GrupoToday.DTO.modelsDto.CateDTO;
import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.impl.mapper.CategoriaMapper;
import org.springframework.stereotype.Service;

import com.GrupoToday.models.Categoria;
import com.GrupoToday.repository.CategoriaRepository;
import com.GrupoToday.service.CategoriaService;

@Service
public class CategoriaImpl implements CategoriaService {
	

	private final CategoriaRepository categoriaRepository;

	private final CategoriaMapper categoriaMapper;
	
	public CategoriaImpl( CategoriaRepository categoriaRepository,
			 CategoriaMapper categoriaMapper) {
		this.categoriaRepository = categoriaRepository;
		this.categoriaMapper = categoriaMapper;
	}


	@Override
	public List<CategoriaDTO> findZapatillaByCategoriaNombre(String nombreCategoria) {
		Categoria categoria = categoriaRepository.findByNombre(nombreCategoria)
				.orElseThrow(() -> new RuntimeException("Categoria No Encontrada: " + nombreCategoria));
		return categoria.getZapatilla().stream()
				.map(zapatilla -> categoriaMapper.searchByCategoryName(
				zapatilla))
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

	@Override
	public Categoria addCategoria(CateDTO categoriaDTO) {
		Categoria agregar = categoriaMapper.mapperCategoria(categoriaDTO);
		return categoriaRepository.save(agregar);
	}

	@Override
	public Categoria update(Integer id, CateDTO categoriaDTO) {
		try {
			Optional<Categoria> actualizar = categoriaRepository.findById(id);
			if (actualizar.isPresent()) {
				Categoria categoria = actualizar.get();
				categoria.setNombre(categoriaDTO.getNombreCategoria());
				return categoriaRepository.save(categoria);
			}else {
				throw new IllegalStateException("Invalido id: " + id);
			}
		}catch (IllegalArgumentException e) {
			System.err.print("ERRO" + e.getMessage());
			return null;
			
		}
	
	}


	@Override
	public void deleteCategoria(Integer id) {
		try {
			Optional<Categoria> eliminar = categoriaRepository.findById(id);
			if (eliminar.isPresent()){
				categoriaRepository.deleteById(id);
			}else {
				throw new IllegalArgumentException("Invalido: " + id);
				
			}
		}catch(IllegalArgumentException e) {
			System.err.print("ERRROR AL ELIMINAR" + e.getMessage());
			
		}
		
	}


}
