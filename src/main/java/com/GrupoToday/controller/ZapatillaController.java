package com.GrupoToday.controller;

import com.GrupoToday.DTO.modelsDto.ZapatillasDto;
import com.GrupoToday.service.ZapatillaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/zapatilla")
@CrossOrigin(origins = "http://localhost:4200")
public class ZapatillaController {

    private final ZapatillaService zapatillaService;
    private final ObjectMapper objectMapper;

    public ZapatillaController(ZapatillaService zapatillaService) {
        this.zapatillaService = zapatillaService;
        this.objectMapper = new ObjectMapper();
    }

    @PostMapping("/add-zapatilla")
    public ResponseEntity<Map<String, Object>> addZapatillaHandler(@RequestPart MultipartFile file,
                                                                   @RequestPart String zapatillaDto) {

        Map<String, Object> response = new HashMap<>();
        try {
            ZapatillasDto zapatilla = convertToDtoZapatillaDto(zapatillaDto);
            zapatillaService.agregarZapatilla(zapatilla, file);
            response.put("message", "Zapatilla Registrada en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IOException e) {
            response.put("message", false);
            response.put("message", "Error al procesar la solicitud: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Ocurrió un error inesperado: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ZapatillasDto convertToDtoZapatillaDto(String zapatillaDtoObj) throws JsonProcessingException {
        return objectMapper.readValue(zapatillaDtoObj, ZapatillasDto.class);
    }
}
