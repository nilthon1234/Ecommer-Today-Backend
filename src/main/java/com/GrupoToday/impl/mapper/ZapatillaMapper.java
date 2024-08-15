package com.GrupoToday.impl.mapper;

import com.GrupoToday.DTO.modelsDto.*;
import com.GrupoToday.DTO.response.BusquedaId;
import com.GrupoToday.models.Administrador;
import com.GrupoToday.models.Categoria;
import com.GrupoToday.models.Marca;
import com.GrupoToday.models.Modelo;
import com.GrupoToday.models.Persona;
import com.GrupoToday.models.Zapatilla;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;

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
    public Zapatilla zapatillaMapper(ZapatillasDto zapatillasDto, Zapatilla zp) {
    	Administrador admin = new Administrador();
        admin.setId(zapatillasDto.getIdAdminZapatillas());
        Modelo mode = new Modelo();
        mode.setId(zapatillasDto.getIdModeloZapatilla());
        Categoria cat = new Categoria();
        cat.setId(zapatillasDto.getIdCategoriaZapatilla());
        Marca mar = new Marca();
        mar.setId(zapatillasDto.getIdMarcaZapatilla());
        Persona per = new Persona();
        per.setId(zapatillasDto.getIdPersonaZapatilla());

        zp.setNombre(zapatillasDto.getNombreZapatilla());
        zp.setDescripcion(zapatillasDto.getDescripcionZapatilla());
        zp.setPrecio(zapatillasDto.getPrecioZapatilla());
        zp.setStock(zapatillasDto.getStockZapatilla());
        zp.setImagen(zapatillasDto.getImagenZapatilla());
        zp.setAdministrador(admin);
        zp.setModelo(mode);
        zp.setCategoria(cat);
        zp.setMarca(mar);
        zp.setPersona(per);
        return zp;
    	
    }
    public ZapatillasDto retornoZapatillaDto(Zapatilla zapatilla) {
        String urlImg = directorioUrl(zapatilla.getImagen());
        return new  ZapatillasDto(
                zapatilla.getId(),
                zapatilla.getNombre(),
                zapatilla.getDescripcion(),
                zapatilla.getStock(),
                zapatilla.getPrecio(),
                zapatilla.getImagen(),
                zapatilla.getAdministrador().getId(),
                zapatilla.getMarca().getId(),
                zapatilla.getCategoria().getId(),
                zapatilla.getModelo().getId(),
                zapatilla.getPersona().getId(),
                urlImg
        );
    }
}
