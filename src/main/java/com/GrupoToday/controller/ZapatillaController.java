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

@RequestMapping("/api/v1/zapatilla")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ZapatillaController {

    private final ZapatillaService zapatillaService;

    public ZapatillaController(ZapatillaService zapatillaService) {
        this.zapatillaService = zapatillaService;
    }
    @PostMapping("/add-zapatilla")
    public ResponseEntity<ZapatillasDto> addZapatillaHandler(@RequestPart MultipartFile file,
                                                             @RequestPart String zapatillaDto) throws IOException {
     ZapatillasDto zapatilla = convertToDtoZapatillaDto(zapatillaDto);
     return new ResponseEntity<>(zapatillaService.agregarZapatilla(zapatilla,file), HttpStatus.CREATED);
    }

    private ZapatillasDto convertToDtoZapatillaDto(String zapatillaDtoObj) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(zapatillaDtoObj, ZapatillasDto.class);
    }

}
