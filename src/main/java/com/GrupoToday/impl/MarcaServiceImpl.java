package com.GrupoToday.impl;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.models.Marca;
import com.GrupoToday.repository.MarcaReposity;
import com.GrupoToday.service.MarcaService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarcaServiceImpl implements MarcaService {
    @Autowired
    private MarcaReposity marcaReposity;
    @Override
    public List<CategoriaDTO> buscarNombreMarca(String nombre) {
        Marca marca = marcaReposity.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada:" + nombre));
        return marca.getZapatilla().stream()
                .map(zapatilla -> new CategoriaDTO(
                        null,
                        null,
                        null,
                        marca.getNombre(),
                        zapatilla.getNombre(),
                        zapatilla.getDescripcion(),
                        zapatilla.getStock(),
                        zapatilla.getPrecio(),
                        null,
                        null,
                        null,
                        null
                ))
               .collect(Collectors.toList())  ;
    }

    @Override
    public List<CategoriaDTO> listMarcas() {
        List<Marca> marcas = marcaReposity.findAll();
        return marcas.stream()
                .map(marc -> new CategoriaDTO(

                        null,
                        null,
                        marc.getId(),
                        marc.getNombre(),
                        null,
                        null,


                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                ))
                .collect(Collectors.toList());
    }

}
