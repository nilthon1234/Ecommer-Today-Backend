package com.GrupoToday.impl;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.ZapatillasDto;
import com.GrupoToday.impl.mapper.ZapatillaMapper;
import com.GrupoToday.models.*;
import com.GrupoToday.repository.ZapatillaRepository;
import com.GrupoToday.service.FileService;
import com.GrupoToday.service.ZapatillaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ZapatillaServiceImpl implements ZapatillaService {

    private final  ZapatillaRepository zapatillaRepository;

    private final FileService fileService;

    private final ZapatillaMapper zapatillaMapper;

    @Value("${proyect.poster}")
    private String path;
    @Value("${base.url}")
    private  String baseUrl;

    public ZapatillaServiceImpl(ZapatillaRepository zapatillaRepository,
                                FileService fileService,
                                ZapatillaMapper zapatillaMapper) {
        this.zapatillaRepository = zapatillaRepository;
        this.fileService = fileService;
        this.zapatillaMapper = zapatillaMapper;
    }
    @Override
    public ZapatillasDto agregarZapatilla(ZapatillasDto zapatillaDto, MultipartFile file) throws IOException{
        String subirNombreArchivo = fileService.subirArchivo(path, file);
        // establecer el valor del campo 'póster' como nombre de archivo
            zapatillaDto.setImagenZapatilla(subirNombreArchivo);

        Zapatilla map= zapatillaMapper.zapatillaMapper(zapatillaDto, new Zapatilla());
        //guardar el objeto zapatilla -> objeto zapatilla guardado
        Zapatilla zapatillaGuardada = zapatillaRepository.save(map);
        // generate the posterUrl
        String urlImg = baseUrl + "/file/" + subirNombreArchivo;
        //asignar el objeto zapatilla guardado al objeto dto y devolverlo

        return zapatillaMapper.retornoZapatillaDto(zapatillaGuardada);
    }

    @Override
    public ZapatillasDto updateZapatilla(Integer id, ZapatillasDto zapatillasDto, MultipartFile file) throws IOException {
        // 1. Verificar si la zapatilla existe
        Zapatilla zapatilla = zapatillaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zapatilla no encontrada"));
        // 2. Actualizar campos de la zapatilla
        // Si el archivo no es nulo y es diferente del archivo existente
        if (file != null && !file.isEmpty()) {
            String fileName = fileService.subirArchivo(path, file);
            zapatillasDto.setImagenZapatilla(fileName);
        }else {
            zapatillasDto.setImagenZapatilla(zapatilla.getImagen());
        }
        Zapatilla save = zapatillaMapper.zapatillaMapper(zapatillasDto, zapatilla);

        // 4. Guardar la zapatilla actualizada
        Zapatilla update = zapatillaRepository.save(save);
        // 6. Devolver el DTO actualizado

        return zapatillaMapper.retornoZapatillaDto(update);
    }
    @Override
    public CategoriaDTO detallsZapatilla(Integer idZapatilla) {
        Optional <Zapatilla> zapatilla = zapatillaRepository.findById(idZapatilla);
        return zapatilla.map(zapatillaMapper::detalles ).orElse(null);
    }
    @Override
    public ZapatillasDto buscarIdZapatilla(Integer idZapatilla) {
        Optional<Zapatilla> zapatilla = zapatillaRepository.findById(idZapatilla);
        return zapatilla.map(zapatillaMapper::listAllZapatillas).orElse(null);
    }
    @Override
    public void deleteZapatilla(Integer id) {
         Optional<Zapatilla> zapatilla = zapatillaRepository.findById(id);
         if (zapatilla.isPresent()){
             Zapatilla zp = zapatilla.get();
             try {
                 zapatillaRepository.deleteById(id);
             }catch (RuntimeException e){
                 e.printStackTrace();
             }
         }else {
             throw new IllegalArgumentException("ZAPATILLA no encontrada");
         }
    }
    @Override
    public List<ZapatillasDto> getAllZapatillas() {
        return zapatillaRepository.findAll()
                .stream()
                .map(zapatillaMapper::listAllZapatillas)
                .collect(Collectors.toList()) ;
    };

}
