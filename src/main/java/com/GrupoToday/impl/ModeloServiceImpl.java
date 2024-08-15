package com.GrupoToday.impl;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.ModeloDto;
import com.GrupoToday.impl.mapper.ModeloMapper;
import com.GrupoToday.models.Modelo;
import com.GrupoToday.repository.ModeloRepository;
import com.GrupoToday.service.ModeloService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModeloServiceImpl implements ModeloService {

	private final ModeloRepository modeloRepository;

	private final ModeloMapper modeloMapper;

	public ModeloServiceImpl(ModeloRepository modeloRepository, ModeloMapper modeloMapper) {
		this.modeloRepository = modeloRepository;
		this.modeloMapper = modeloMapper;

	}

	@Override
	public List<CategoriaDTO> listAllModelos() {

		List<Modelo> modelos = modeloRepository.findAll();
		return modelos.stream().map(modeloMapper::listModeloDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Modelo add(ModeloDto modeloDto) {
		Modelo modelo = modeloMapper.modeloMapper(modeloDto);
		return modeloRepository.save(modelo);
	}

	@Override
	public Modelo update(Integer id, ModeloDto modeloDto) {

		return modeloRepository.findById(id)
		.map(modelo -> {
			modelo.setNombre(modeloDto.getNombreModeloZapatilla());
			return modeloRepository.save(modelo);
		})
		.orElseThrow(() -> new IllegalStateException("Invalido id: " + id));
	} 

	@Override
	public void delete(Integer id) {

		modeloRepository.deleteById(id);
	}
}
