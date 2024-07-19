package com.GrupoToday.impl;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.models.Modelo;
import com.GrupoToday.repository.ModeloRepository;
import com.GrupoToday.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModeloServiceImpl implements ModeloService {
    @Autowired
    private ModeloRepository modeloRepository;
    @Override
    public List<CategoriaDTO> listAllModelos() {

        List<Modelo> modelos = modeloRepository.findAll();
        return  modelos.stream()
                .map( mode -> new CategoriaDTO(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        mode.getId(),
                        mode.getNombre(),
                        null,
                        null
                )).collect(Collectors.toList());
    }
}
