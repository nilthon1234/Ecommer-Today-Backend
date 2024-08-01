package com.GrupoToday.controller;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.ZapatillasDto;
import com.GrupoToday.DTO.response.BusquedaId;
import com.GrupoToday.service.ZapatillaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/list-zapatilla")
    public ResponseEntity<List<ZapatillasDto>> getAllZapatillas() {
        List<ZapatillasDto> zapatillas = zapatillaService.getAllZapatillas();
        return ResponseEntity.ok(zapatillas);
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
            response.put("message", "Error al procesar la solicitud: ");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.put("message", "Occurring un error unexpected: ");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update-zapatilla/{id}")
    public ResponseEntity <Map<String, String>> updateZapatillaHandler(@PathVariable Integer id,
                                                                   @RequestPart MultipartFile file,
                                                                   @RequestPart String objectZapatillaDto) throws IOException {
    if (file.isEmpty()) {
        file = null;
    }

    ZapatillasDto zapatillasDto = convertToDtoZapatillaDto(objectZapatillaDto);
    boolean isUpdate = zapatillaService.actualizarZapatilla(id,zapatillasDto,file);
    Map<String, String> response = new HashMap<>();
    if (isUpdate) {
        response.put("message", "Zapatilla actualizada con éxito");
        return ResponseEntity.ok(response);
    }else {
        response.put("message", "No se encontró la zapatilla");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity <Map<String, String>> eliminarZapatilla(@PathVariable Integer id){
        try {
            zapatillaService.deleteZapatilla(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Zapatilla eliminada con éxito");
            return  ResponseEntity.ok(response);
        } catch (Exception e) {

            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al eliminar la zapatilla");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    private ZapatillasDto convertToDtoZapatillaDto(String zapatillaDtoObj) throws JsonProcessingException {
        return objectMapper.readValue(zapatillaDtoObj, ZapatillasDto.class);
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
