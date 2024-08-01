package com.GrupoToday.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusquedaId {

    private Integer idZapatilla;
    private String nombreZapatilla;
    private String descripcionZapatilla;
    private String precioZapatilla;
    private String stockZapatilla;
    private String imagenZapatilla;
    private String nombreCategoria;
    private String gmailAdmin;
    private String nombreMarca;
    private String nombrePersona;
    private String nombreModelo;
    private String urlImg;
}
