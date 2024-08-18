package com.GrupoToday.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VentaDto {

    private String nombreCliente;
    private String apellidoCliente;
    private String gmailCliente;
    private int telefonoCliente;
    private String direccionCliente;
    private int departamentoCliente;


}
