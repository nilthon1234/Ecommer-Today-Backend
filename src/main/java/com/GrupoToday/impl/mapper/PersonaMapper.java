package com.GrupoToday.impl.mapper;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.models.Persona;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {

    public CategoriaDTO listarPersona(Persona persona) {
        CategoriaDTO lst = new CategoriaDTO();
        lst.setIdPersonaZapatilla(persona.getId());
        lst.setNombrePersonaZapatilla(persona.getNombre());
        return lst;
    }
}
