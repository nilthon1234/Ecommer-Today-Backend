package com.GrupoToday.impl;

import com.GrupoToday.DTO.modelsDto.DepartamentoDto;
import com.GrupoToday.impl.mapper.DepartamentoMapper;
import com.GrupoToday.models.Departamento;
import com.GrupoToday.repository.DepartamentoRepository;
import com.GrupoToday.service.DepartamentoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final DepartamentoMapper departamentoMapper;

    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository,
                                   DepartamentoMapper departamentoMapper){
        this.departamentoRepository = departamentoRepository;
        this.departamentoMapper = departamentoMapper;
    }
    @Override
    public List<DepartamentoDto> getAllDepartamento() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        return departamentos.stream()
                .map(departamentoMapper::mapperList)
                .collect(Collectors.toList());
    }
}
