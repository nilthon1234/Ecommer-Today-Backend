package com.GrupoToday.impl;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.MarcaDto;
import com.GrupoToday.impl.mapper.MarcaMapper;
import com.GrupoToday.models.Marca;
import com.GrupoToday.repository.MarcaReposity;
import com.GrupoToday.service.MarcaService;
import com.fasterxml.jackson.annotation.JsonInclude;
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
                .map(zapatilla ->  marcaMapper.searchBrandName(
                         zapatilla))
                .collect(Collectors.toList())  ;
    }

    @Override
    public List<CategoriaDTO> listMarcas() {
        List<Marca> marcas = marcaReposity.findAll();
        return marcas.stream()
                .map(marc -> marcaMapper.listaMarca(marc))
                .collect(Collectors.toList());
    }

    @Override
    public Marca addMarca(MarcaDto marcaDto) {
        Marca marca = marcaMapper.mapeadorMarca(marcaDto);
        return marcaReposity.save(marca);
    }

    @Override
    public Marca updateMarca(Integer id, MarcaDto marcaDto ) {
        try {
        Optional<Marca> actualizar = marcaReposity.findById(id);
            if(actualizar.isPresent()){
                Marca marca = actualizar.get();
                marca.setNombre(marcaDto.getNombreMarca());
                return marcaReposity.save(marca);
            }else {
                throw new IllegalStateException("Invalid" + id);
            }
        } catch (IllegalStateException  e){
            System.err.println("Error al actualizar" + e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteMarca(Integer id) {
        try {
            Optional<Marca> borrar = marcaReposity.findById(id);
            if(borrar.isPresent()){
                marcaReposity.deleteById(id);
            }else {
                throw new IllegalStateException("Invalid" + id);
            }

        }catch (IllegalStateException e){
            System.err.println("Error al borrar" + e.getMessage());
        }
    }

}
