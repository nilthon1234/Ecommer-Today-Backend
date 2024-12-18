package com.GrupoToday.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.GrupoToday.DTO.modelsDto.CateDTO;
import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.impl.mapper.CategoriaMapper;

import org.apache.catalina.users.AbstractUser;
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
				.map(categoriaMapper::searchByCategoryName)
				.collect(Collectors.toList());
	}

	@Override
	public List<CategoriaDTO> listarTodo() {
		List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
				.map(categoriaMapper::categoryFrom)
				.collect(Collectors.toList());

	}

	@Override
	public Categoria addCategoria(CateDTO categoriaDTO) {
		Categoria agregar = categoriaMapper.mapperCategoria(categoriaDTO);
		return categoriaRepository.save(agregar);
	}

	@Override
	public Categoria update(Integer id, CateDTO categoriaDTO) {
		return categoriaRepository.findById(id)
				.map(actus -> {
					actus.setNombre(categoriaDTO.getNombreCategoria());
					return categoriaRepository.save(actus);
				})
				.orElseThrow(() -> new IllegalStateException("Invalido Id: " + id));
	
	}


	@Override
	public void deleteCategoria(Integer id) {
		categoriaRepository.deleteById(id);
		
	}


}
