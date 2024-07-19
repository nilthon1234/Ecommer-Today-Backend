package com.GrupoToday.DTO.modelsDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZapatillasDto {

    private Integer idZapatilla;
    private String nombreZapatilla;
    private String descripcionZapatilla;
    private String precioZapatilla;
    private String stockZapatilla;
    private String imagenZapatilla;
    private int idAdminZapatillas;
    private int idModeloZapatilla;
    private int idCategoriaZapatilla;
    private int idMarcaZapatilla;
    private int idPersonaZapatilla;
    private String urlImg;



}
