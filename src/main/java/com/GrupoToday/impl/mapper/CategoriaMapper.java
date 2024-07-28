package com.GrupoToday.impl.mapper;


import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.models.Categoria;
import com.GrupoToday.models.Zapatilla;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriaMapper
{


    public CategoriaDTO categoryFrom(Categoria category) {
        CategoriaDTO categoria = new CategoriaDTO();
        categoria.setNombreCategoria(category.getNombre());
        categoria.setIdCategoria(category.getId());
        return categoria;
    }
    public CategoriaDTO searchByCategoryName(Categoria categoria, Zapatilla zapatilla){
        CategoriaDTO cate = new CategoriaDTO();
        cate.setIdCategoria(categoria.getId());
        cate.setNombreCategoria(categoria.getNombre());
        cate.setNombreZapatilla(zapatilla.getNombre());
        cate.setMarcaZapatilla(zapatilla.getMarca().getNombre());
        cate.setDescriptionZapatilla(zapatilla.getDescripcion());
        cate.setStockZapatilla(zapatilla.getStock());
        cate.setPrecioZapatilla(zapatilla.getPrecio());
        cate.setImagenZapatilla(zapatilla.getImagen());
        cate.setUrlImg("http://localhost:8080/file/" + zapatilla.getImagen());

        return cate;
    }

}
