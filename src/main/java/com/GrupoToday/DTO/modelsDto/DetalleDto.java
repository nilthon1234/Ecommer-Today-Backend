package com.GrupoToday.DTO.modelsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetalleDto {

    private  Integer idZapatilla;
    private int cantidad;
    private int precioZapatilla;
    private Double subTotal;
}
