package com.GrupoToday.impl.mapper;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.ModeloDto;
import com.GrupoToday.models.Modelo;
import org.springframework.stereotype.Component;

@Component
public class ModeloMapper {
    public CategoriaDTO listModeloDTO(Modelo modelo) {
        CategoriaDTO lst =  new CategoriaDTO();
        lst.setIdModeloZapatilla(modelo.getId());
        lst.setNombreModeloZapatilla(modelo.getNombre());
        return lst;
    }
    
    public Modelo modeloMapper(ModeloDto modeloDto) {
    	Modelo md = new Modelo();
    	md.setId(modeloDto.getIdModeloZapatilla());
    	md.setNombre(modeloDto.getNombreModeloZapatilla());
    	return md;
    }
}
