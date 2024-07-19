package com.GrupoToday.impl.mapper;


import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import org.springframework.stereotype.Component;

@Component
public class ZapatillaMapper {

    public CategoriaDTO categoryFrom(
            String category, Integer id) {
        CategoriaDTO categoria = new CategoriaDTO();
        categoria.setNombreCategoria(category);
        categoria.setIdCategoria(id);
        return categoria;
    }


}
