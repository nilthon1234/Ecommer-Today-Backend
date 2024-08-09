package com.GrupoToday.impl.mapper;


import com.GrupoToday.DTO.modelsDto.CateDTO;
import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.models.Categoria;
import com.GrupoToday.models.Marca;
import com.GrupoToday.models.Zapatilla;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriaMapper
{
    @Value("${base.url}")
    private String baseUrl;

    private String directorioUrl(String myNombreImg){
        return baseUrl + "/file/" + myNombreImg;
    }

    public CategoriaDTO categoryFrom(Categoria category) {
        CategoriaDTO categoria = new CategoriaDTO();
        categoria.setNombreCategoria(category.getNombre());
        categoria.setIdCategoria(category.getId());
        return categoria;
    }
    public CategoriaDTO searchByCategoryName( Zapatilla zapatilla){
        CategoriaDTO cate = new CategoriaDTO();
        cate.setIdCategoria(zapatilla.getCategoria().getId());
        cate.setNombreCategoria(zapatilla.getCategoria().getNombre());
        cate.setNombreZapatilla(zapatilla.getNombre());
        cate.setMarcaZapatilla(zapatilla.getMarca().getNombre());
        cate.setDescriptionZapatilla(zapatilla.getDescripcion());
        cate.setPrecioZapatilla(zapatilla.getPrecio());
        cate.setStockZapatilla(zapatilla.getStock());
        cate.setImagenZapatilla(zapatilla.getImagen());
        cate.setUrlImg(directorioUrl(zapatilla.getImagen()));

        return cate;
    }
    public Categoria mapperCategoria(CateDTO categoriaDTO) {
    	
    	Categoria ct = new Categoria();
    	ct.setId(categoriaDTO.getIdCategoria());
    	ct.setNombre(categoriaDTO.getNombreCategoria());
    	
    	return ct;
    }

}
