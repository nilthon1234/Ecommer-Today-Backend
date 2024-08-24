package com.GrupoToday.impl.mapper;

import com.GrupoToday.DTO.modelsDto.DepartamentoDto;
import com.GrupoToday.models.Departamento;
import org.springframework.stereotype.Component;

@Component
public class DepartamentoMapper {

    public DepartamentoDto mapperList(Departamento departamento){
        DepartamentoDto dp = new DepartamentoDto();
        dp.setIdDepartamento(departamento.getId());
        dp.setNombreDepartamento(departamento.getNombre());
        return dp;
    }
}
