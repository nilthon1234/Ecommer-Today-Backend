package com.GrupoToday.controller;

import com.GrupoToday.DTO.response.RequesVenta;
import com.GrupoToday.service.VentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/public/venta")
public class VentaController {
    private final VentaService ventaService;
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping("/boleta")
    public ResponseEntity<Map<String, String>> registrarVenta(@RequestBody RequesVenta request){
        Map< String, String> response = new HashMap<>();
        try {

            ventaService.registrarVenta(request.getVentaDto(), request.getDetalles());
            response.put("message", "Venta Registrada");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            response.put("message", "Ocurrio un error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
