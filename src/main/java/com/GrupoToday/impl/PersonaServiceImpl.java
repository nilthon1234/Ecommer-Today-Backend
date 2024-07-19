package com.GrupoToday.impl;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.models.Persona;
import com.GrupoToday.repository.PersonaRepository;
import com.GrupoToday.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    private PersonaRepository personaRepository;
    @Override
    public List<CategoriaDTO> listAllPersona() {
        List<Persona> personas = personaRepository.findAll();
        return personas.stream()
                .map(pers -> new CategoriaDTO(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        pers.getId(),
                        pers.getNombre()
                )).collect(Collectors.toList());
    }
}
