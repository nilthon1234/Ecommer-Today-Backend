package com.GrupoToday.DTO.modelsDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {

    //Categoria
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idCategoria;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombreCategoria;
    //Marca
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idMarcaZapatilla;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String marcaZapatilla;
    //Zapatilla
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombreZapatilla;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String descriptionZapatilla;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String stockZapatilla;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String precioZapatilla;

    //modelo
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idModeloZapatilla;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombreModeloZapatilla;
    //persona
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idPersonaZapatilla;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombrePersonaZapatilla;

    private String imagenZapatilla;
    private String urlImg;



}
