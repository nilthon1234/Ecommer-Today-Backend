package com.GrupoToday.controller;

import com.GrupoToday.DTO.response.RequesVenta;
import com.GrupoToday.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venta")
public class VentaController {
    private final VentaService ventaService;
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping("/boleta")
    public ResponseEntity<?> registrarVenta(@RequestBody RequesVenta request){
        ventaService.registrarVenta(request.getVentaDto(), request.getDetalles());
        return ResponseEntity.ok().build();
    }
}
