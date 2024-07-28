package com.GrupoToday.impl.mapper;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.ZapatillasDto;
import com.GrupoToday.models.Zapatilla;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ZapatillaMapper {

    @Value("${base.url}")
    private String baseUrl;
    private String directorioUrl(String myUrl){
        return baseUrl + "/file/" + myUrl;
    }
    public CategoriaDTO detalles(Zapatilla zapatilla) {
        CategoriaDTO cate = new CategoriaDTO();
        cate.setIdZapatilla(zapatilla.getId());
        cate.setNombreZapatilla(zapatilla.getNombre());
        cate.setDescriptionZapatilla(zapatilla.getDescripcion());
        cate.setPrecioZapatilla(zapatilla.getPrecio());
        cate.setStockZapatilla(zapatilla.getStock());
        cate.setImagenZapatilla(zapatilla.getImagen());
        cate.setGmailAdmin(zapatilla.getAdministrador().getGmail());
        cate.setMarcaZapatilla(zapatilla.getMarca().getNombre());
        cate.setNombreModeloZapatilla(zapatilla.getModelo().getNombre());
        cate.setNombreCategoria(zapatilla.getCategoria().getNombre());
        cate.setNombrePersonaZapatilla(zapatilla.getPersona().getNombre());
        cate.setUrlImg(directorioUrl(zapatilla.getImagen()));


        return cate;
    }

    public ZapatillasDto listAllZapatillas(Zapatilla zapatilla){
        ZapatillasDto zapa = new ZapatillasDto();
        zapa.setIdZapatilla(zapatilla.getId());
        zapa.setNombreZapatilla(zapatilla.getNombre());
        zapa.setDescripcionZapatilla(zapatilla.getDescripcion());
        zapa.setPrecioZapatilla(zapatilla.getPrecio());
        zapa.setStockZapatilla(zapatilla.getStock());
        zapa.setImagenZapatilla(zapatilla.getImagen());
        zapa.setIdAdminZapatillas(zapatilla.getAdministrador().getId());
        zapa.setIdModeloZapatilla(zapatilla.getModelo().getId());
        zapa.setIdCategoriaZapatilla(zapatilla.getCategoria().getId());
        zapa.setIdMarcaZapatilla(zapatilla.getMarca().getId());
        zapa.setIdPersonaZapatilla(zapatilla.getPersona().getId());
        zapa.setUrlImg(directorioUrl(zapatilla.getImagen()));
        return zapa;
    }
}
