package com.GrupoToday.controller;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.ZapatillasDto;
import com.GrupoToday.service.ZapatillaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
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

    private ZapatillasDto convertToDtoZapatillaDto(String zapatillaDtoObj) throws JsonProcessingException {
        return objectMapper.readValue(zapatillaDtoObj, ZapatillasDto.class);
    }

    @GetMapping("/list-zapatilla")
    public ResponseEntity<List<ZapatillasDto>> getAllZapatillas() {
        List<ZapatillasDto> zapatillas = zapatillaService.getAllZapatillas();
        return ResponseEntity.ok(zapatillas);
    }
    @PostMapping("/add-zapatilla")
    public ResponseEntity<Map<String, String>> addZapatillaHandler(@RequestPart MultipartFile file,
                                                                   @RequestPart String zapatillaDto) {

        Map<String, String> response = new HashMap<>();
        try {
            ZapatillasDto zapatilla = convertToDtoZapatillaDto(zapatillaDto);
            zapatillaService.agregarZapatilla(zapatilla, file);
            response.put("message", "Zapatilla Registrada");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("message", "Occurring un error unexpected: ");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-zapatilla/{id}")
    public ResponseEntity <ZapatillasDto> actualizarZapatilla(@PathVariable Integer id,
                                                              @RequestPart MultipartFile file,
                                                              @RequestPart String objectZapatillaDto)throws Exception {
            if (file.isEmpty()) file = null;
            ZapatillasDto zapatillasDto = convertToDtoZapatillaDto(objectZapatillaDto);
            return ResponseEntity.ok(zapatillaService.updateZapatilla(id, zapatillasDto, file));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteZapatilla(@PathVariable Integer id){
        try {
            zapatillaService.deleteZapatilla(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "zapatilla Eliminado correctamente");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al eliminar zapatilla");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarZapatilla(@PathVariable Integer id){
        CategoriaDTO zapatilla = zapatillaService.detallsZapatilla(id);
        return zapatilla != null ? ResponseEntity.ok(zapatilla) : ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity <ZapatillasDto> buscarId(@PathVariable Integer id){
        ZapatillasDto busqueda = zapatillaService.buscarIdZapatilla(id);
        return busqueda != null ? ResponseEntity.ok(busqueda) : ResponseEntity.notFound().build() ;
    }
}
