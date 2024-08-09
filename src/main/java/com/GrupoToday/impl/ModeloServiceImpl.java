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
    
    public ModeloServiceImpl(ModeloRepository modeloRepository,
    						ModeloMapper modeloMapper) {
    	this.modeloRepository = modeloRepository;
    	this.modeloMapper = modeloMapper;
    	
    }
    
    @Override
    public List<CategoriaDTO> listAllModelos() {

        List<Modelo> modelos = modeloRepository.findAll();
        return  modelos.stream()
                .map( mode -> modeloMapper.listModeloDTO (mode))
                .collect(Collectors.toList());
    }
	@Override
	public Modelo add(ModeloDto modeloDto) {
		Modelo modelo = modeloMapper.modeloMapper(modeloDto);
		return modeloRepository.save(modelo);
	}
	@Override
	public Modelo update(Integer id, ModeloDto modeloDto) {
		try {
			Optional<Modelo> actualizar= modeloRepository.findById(id);
			if (actualizar.isPresent()){
				Modelo modelo = actualizar.get();
				modelo.setNombre(modeloDto.getNombreModelo());
				return modeloRepository.save(modelo);
			}else {
				throw new IllegalStateException("Invalido id:" + id);
			}
		}catch (IllegalArgumentException e){
			System.err.print("error en consola" + e.getMessage());
			
		}
		return null;
	}
	@Override
	public void delete(Integer id) {
		try {
			Optional<Modelo> eliminar = modeloRepository.findById(id);
			
			if(eliminar.isPresent()) {
				modeloRepository.deleteById(id);
				
			}else {
				throw new IllegalStateException("Error invalido id: " + id);
			}
		}catch (IllegalArgumentException e){
			System.err.print("Error en la CONSOLA" + e.getMessage());
			
		}
	
	}
}
