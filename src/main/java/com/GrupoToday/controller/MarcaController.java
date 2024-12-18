package com.GrupoToday.controller;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.MarcaDto;
import com.GrupoToday.service.MarcaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/marca")
@CrossOrigin(origins = "http://localhost:4200")
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping("/list-all")
    public List<MarcaDto> listarMarca(){
        return marcaService.listMarcas();
    }
    public List<MarcaDto> listAllMarca(){
        return marcaService.listMarcas();
    }

    @GetMapping("/buscar")
    public List<CategoriaDTO> buscarMarca(@RequestParam String nombre) {
        return marcaService.buscarNombreMarca(nombre);
    }
    @PostMapping("/add")
    public ResponseEntity <Map<String, String>> addMarca(@RequestBody MarcaDto marcaDto) {
        Map<String, String> response = new HashMap<>();
            try {
                marcaService.addMarca(marcaDto);
                response.put("message", "Marca agregada correctamente");
                return new  ResponseEntity<>(response, HttpStatus.CREATED);
            }catch (Exception e) {
                response.put("message", "Error al agregar la marca");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity <Map<String, String>> updateMarca(@PathVariable Integer id, @RequestBody MarcaDto marcaDto) {
        
            Map<String, String> response = new HashMap<>();
            if (id != null) {
                marcaService.updateMarca(id, marcaDto);
                response.put("message", "Marca actualizada correctamente");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }else {
                response.put("message", "El id de la marca no existe");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> eliminarMaerca( @PathVariable Integer id){
        Map<String, String> response = new HashMap<>();
        
             marcaService.deleteMarca(id);
            if (response.containsKey("message")){

                response.put("message", "Marca no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else {
                response.put("message", "Marca eliminada correctamente");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        
    }
}
