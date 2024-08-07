package com.GrupoToday.impl.mapper;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.MarcaDto;
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
        return baseUrl + "/file/" + myNombreImg;
    }

    public CategoriaDTO searchBrandName( Zapatilla zapatilla){
        CategoriaDTO cate = new CategoriaDTO();
        cate.setMarcaZapatilla(zapatilla.getMarca().getNombre());
        cate.setNombreZapatilla(zapatilla.getNombre());
        cate.setDescriptionZapatilla(zapatilla.getDescripcion());
        cate.setStockZapatilla(zapatilla.getStock());
        cate.setPrecioZapatilla(zapatilla.getPrecio());
        cate.setImagenZapatilla(zapatilla.getImagen());
        cate.setUrlImg(myUrl(zapatilla.getImagen()));
        return cate;
    }
    public CategoriaDTO listaMarca(Marca marca){
        CategoriaDTO marc = new CategoriaDTO();
        marc.setIdMarcaZapatilla(marca.getId());
        marc.setMarcaZapatilla(marca.getNombre());
        return marc;
    }

    public Marca mapeadorMarca(MarcaDto marcaDto){
        Marca marca = new Marca();
        marca.setId(marcaDto.getIdMaraca());
        marca.setNombre(marcaDto.getNombreMarca());
        return marca;
    }

}
