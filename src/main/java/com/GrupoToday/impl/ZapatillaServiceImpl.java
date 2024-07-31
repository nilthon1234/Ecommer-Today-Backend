package com.GrupoToday.impl;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.ZapatillasDto;
import com.GrupoToday.impl.mapper.ZapatillaMapper;
import com.GrupoToday.models.*;
import com.GrupoToday.repository.ZapatillaRepository;
import com.GrupoToday.service.FileService;
import com.GrupoToday.service.ZapatillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ZapatillaServiceImpl implements ZapatillaService {
    @Autowired
    private  ZapatillaRepository zapatillaRepository;
    @Autowired
    private  FileService fileService;
    @Autowired
    private ZapatillaMapper zapatillaMapper;

    @Value("${proyect.poster}")
    private String path;
    @Value("${base.url}")
    private  String baseUrl;

    public ZapatillaServiceImpl() {
    }

    //    public ZapatillaServiceImpl(ZapatillaRepository zapatillaRepository, FileService fileService)  {
//        this.zapatillaRepository = zapatillaRepository;
//        this.fileService = fileService;
//    }
    @Override
    public ZapatillasDto agregarZapatilla(ZapatillasDto zapatillaDto, MultipartFile file) throws IOException{

        // cargar el archivo
        if (Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))){
            throw new RuntimeException("El archivo ya existe");
        }
            String subirNombreArchivo = fileService.subirArchivo(path, file);
        // establecer el valor del campo 'póster' como nombre de archivo
            zapatillaDto.setImagenZapatilla(subirNombreArchivo);

        Administrador administrador = new Administrador();
        administrador.setId(zapatillaDto.getIdAdminZapatillas());
        Modelo modelo = new Modelo();
        modelo.setId(zapatillaDto.getIdModeloZapatilla());
        Categoria categoria = new Categoria();
        categoria.setId(zapatillaDto.getIdCategoriaZapatilla());
        Marca marca = new Marca();
        marca.setId(zapatillaDto.getIdMarcaZapatilla());
        Persona persona = new Persona();
        persona.setId(zapatillaDto.getIdPersonaZapatilla());
        //asignar dto al objeto zapatilla
            Zapatilla zapatilla = new Zapatilla(
                    null,
                    zapatillaDto.getNombreZapatilla(),
                    zapatillaDto.getDescripcionZapatilla(),
                    zapatillaDto.getPrecioZapatilla(),
                    zapatillaDto.getStockZapatilla(),
                    zapatillaDto.getImagenZapatilla(),
                    administrador,
                    modelo,
                    categoria,
                    marca,
                    persona
            );


        //guardar el objeto zapatilla -> objeto zapatilla guardado
    Zapatilla zapatillaGuardada = zapatillaRepository.save(zapatilla);
        // generate the posterUrl
    String urlImg = baseUrl + "/file/" + subirNombreArchivo;
        //asignar el objeto zapatilla guardado al objeto dto y devolverlo
    ZapatillasDto response = new ZapatillasDto(
            zapatillaGuardada.getId(),
            zapatillaGuardada.getNombre(),
            zapatillaGuardada.getDescripcion(),
            zapatillaGuardada.getPrecio(),
            zapatillaGuardada.getStock(),
            zapatillaGuardada.getImagen(),
            zapatillaGuardada.getAdministrador().getId(),
            zapatillaGuardada.getModelo().getId(),
            zapatillaGuardada.getCategoria().getId(),
            zapatillaGuardada.getMarca().getId(),
            zapatillaGuardada.getPersona().getId(),
            urlImg
    );

        return response;
    }

    @Override
    public ZapatillasDto actualizarZapatilla(Integer id, ZapatillasDto zapatillasDto, MultipartFile file) throws IOException{
        Zapatilla zapa = zapatillaRepository.findById(id).orElseThrow( () -> new RuntimeException("Zapatilla no encontrada"));
        String nombreArchivo = zapa.getImagen();
        if (file != null){
            Files.deleteIfExists(Paths.get(path + File.separator + nombreArchivo ));
            nombreArchivo = fileService.subirArchivo(path, file);
        }
        zapatillasDto.setImagenZapatilla(nombreArchivo);
        Zapatilla zapatilla = new Zapatilla(
                zapa.getId(),
                zapatillasDto.getNombreZapatilla(),
                zapatillasDto.getDescripcionZapatilla(),
                zapatillasDto.getPrecioZapatilla(),
                zapatillasDto.getStockZapatilla(),
                zapatillasDto.getImagenZapatilla(),
                zapa.getAdministrador(),
                zapa.getModelo(),
                zapa.getCategoria(),
                zapa.getMarca(),
                zapa.getPersona()
        );
        Zapatilla zapatillaGuardada = zapatillaRepository.save(zapatilla);
        String urlImg = baseUrl + "/file/" + nombreArchivo;
        ZapatillasDto response =new ZapatillasDto(
                zapatillaGuardada.getId(),
                zapatillaGuardada.getNombre(),
                zapatillaGuardada.getDescripcion(),
                zapatillaGuardada.getPrecio(),
                zapatillaGuardada.getStock(),
                zapatillaGuardada.getImagen(),
                zapatillaGuardada.getAdministrador().getId(),
                zapatillaGuardada.getModelo().getId(),
                zapatillaGuardada.getCategoria().getId(),
                zapatillaGuardada.getMarca().getId(),
                zapatillaGuardada.getPersona().getId(),
                urlImg
        );
        return response;

    }
    @Override
    public CategoriaDTO detallsZapatilla(Integer idZapatilla) {
        Optional <Zapatilla> zapatilla = zapatillaRepository.findById(idZapatilla);
        return zapatilla.map(zapatillaMapper::detalles ).orElse(null);
    }

    @Override
    public String deleteZapatilla(Integer id) throws IOException {
        Zapatilla zp = zapatillaRepository.findById(id).orElseThrow(() -> new RuntimeException("Zapatilla no encontrada"));

        Files.deleteIfExists(Paths.get(path + File.separator + zp.getImagen()));
        zapatillaRepository.deleteById(id);
        return "Zapatilla delete with id = " + id;
    }


    @Override
    public List<ZapatillasDto> getAllZapatillas() {
        return zapatillaRepository.findAll()
                .stream()
                .map(zapatilla -> zapatillaMapper.listAllZapatillas(
                        zapatilla
                ))
                .collect(Collectors.toList()) ;
    };

}
