package com.GrupoToday.service;

import com.GrupoToday.DTO.modelsDto.DetalleDto;
import com.GrupoToday.DTO.response.VentaDto;

import java.util.List;

public interface VentaService {
void registrarVenta(VentaDto ventaDto, List<DetalleDto> detalles);

}
