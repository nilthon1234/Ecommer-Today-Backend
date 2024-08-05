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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AdministradorDto> administradorDtos;


    //Modelo
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int idModeloZapatilla;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ModeloDto> modeloDtos;

    //Categoria
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private int idCategoriaZapatilla;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List <CategoriaDTO> categoriaDtos;

    //Marca
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int idMarcaZapatilla;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MarcaDto> marcaDtos;
    //Persona
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int idPersonaZapatilla;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PersonaDto> personaDtos;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String urlImg;



}
