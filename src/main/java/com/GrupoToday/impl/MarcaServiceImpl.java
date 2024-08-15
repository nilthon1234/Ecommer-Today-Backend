package com.GrupoToday.impl;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.MarcaDto;
import com.GrupoToday.impl.mapper.MarcaMapper;
import com.GrupoToday.models.Marca;
import com.GrupoToday.repository.MarcaReposity;
import com.GrupoToday.service.MarcaService;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.apache.catalina.users.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarcaServiceImpl implements MarcaService {
    @Autowired
    private MarcaReposity marcaReposity;
    @Autowired
    private MarcaMapper marcaMapper;
    @Override
    public List<CategoriaDTO> buscarNombreMarca(String nombre) {
        Marca marca = marcaReposity.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada:" + nombre));
        return marca.getZapatilla().stream()
                .map(marcaMapper::searchBrandName)
                .collect(Collectors.toList())  ;
    }

    @Override
    public List<MarcaDto> listMarcas() {
        List<Marca> marca = marcaReposity.findAll();
        return marca.stream()
                .map(marcaMapper::listaMarca)
                .collect(Collectors.toList());
    }

    @Override
    public Marca addMarca(MarcaDto marcaDto) {
        Marca marca = marcaMapper.mapeadorMarca(marcaDto);
        return marcaReposity.save(marca);
    }

    @Override
    public Marca updateMarca(Integer id, MarcaDto marcaDto ) {
        return marcaReposity.findById(id)
        		.map(actus -> {
        			actus.setNombre(marcaDto.getMarcaZapatilla());
        			return marcaReposity.save(actus);
        		})
        		.orElseThrow(() -> new IllegalStateException("Invalido id:" +  id));
    }

    @Override
    public void deleteMarca(Integer id) {
    	marcaReposity.deleteById(id);
    }

}
