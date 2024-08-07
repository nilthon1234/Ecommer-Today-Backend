package com.GrupoToday.DTO.modelsDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZapatillasDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idZapatilla;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombreZapatilla;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String descripcionZapatilla;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String precioZapatilla;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String stockZapatilla;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String imagenZapatilla;

    //Administrador
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idAdminZapatillas;


    //Modelo
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idModeloZapatilla;

    //Categoria
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idCategoriaZapatilla;

    //Marca
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idMarcaZapatilla;
    //Persona
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idPersonaZapatilla;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String urlImg;



}
