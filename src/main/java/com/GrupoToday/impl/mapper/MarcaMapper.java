package com.GrupoToday.impl.mapper;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.models.Marca;
import com.GrupoToday.models.Zapatilla;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MarcaMapper {
    @Value("${base.url}")
    private String baseUrl;
    private String myUrl(String myNombreImg){
        return baseUrl + "/file/";
    }

    public CategoriaDTO searchBrandName(Marca marca, Zapatilla zapatilla){
        CategoriaDTO cate = new CategoriaDTO();
        cate.setMarcaZapatilla(marca.getNombre());
        cate.setNombreZapatilla(zapatilla.getNombre());
        cate.setDescriptionZapatilla(zapatilla.getDescripcion());
        cate.setStockZapatilla(zapatilla.getStock());
        cate.setPrecioZapatilla(zapatilla.getPrecio());
        cate.setUrlImg(myUrl(zapatilla.getImagen()));
        return cate;
    }
    public CategoriaDTO listaMarca(Marca marca){
        CategoriaDTO marc = new CategoriaDTO();
        marc.setIdMarcaZapatilla(marca.getId());
        marc.setMarcaZapatilla(marca.getNombre());
        return marc;
    }

}
