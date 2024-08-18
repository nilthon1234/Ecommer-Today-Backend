package com.GrupoToday.DTO.response;

import com.GrupoToday.DTO.modelsDto.DetalleDto;
import lombok.Data;

import java.util.List;

@Data
public class RequesVenta {

    private  VentaDto ventaDto;
    private List<DetalleDto> detalles;
}
