package com.GrupoToday.impl;

import com.GrupoToday.DTO.modelsDto.ZapatillasDto;
import com.GrupoToday.models.*;
import com.GrupoToday.repository.ZapatillaRepository;
import com.GrupoToday.service.FileService;
import com.GrupoToday.service.ZapatillaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZapatillaServiceImpl implements ZapatillaService {

    private final ZapatillaRepository zapatillaRepository;
    private final FileService fileService;

    @Value("${proyect.poster}")
    private String path;
    @Value("${base.url}")
    private  String baseUrl;
    public ZapatillaServiceImpl(ZapatillaRepository zapatillaRepository, FileService fileService)  {
        this.zapatillaRepository = zapatillaRepository;
        this.fileService = fileService;
    }
    @Override
    public ZapatillasDto agregarZapatilla(ZapatillasDto zapatillaDto, MultipartFile file) throws IOException{
        // cargar el archivo
            String subirNombreArchivo = fileService.subirArchivo(path, file);
        // set the value of field 'poster' as filename
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
        //map dto to zapatilla object
            Zapatilla zapatilla = new Zapatilla(
                    zapatillaDto.getIdZapatilla(),
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
    public ZapatillasDto obtenerZapatilla(Integer zapatillaId) {
        return null;
    }

    @Override
    public List<ZapatillasDto> getAllZapatillas() {
        return zapatillaRepository.findAll()
                .stream()
                .map(zapatilla -> new ZapatillasDto(
                         zapatilla.getId(),
                         zapatilla.getNombre(),
                         zapatilla.getDescripcion(),
                         zapatilla.getPrecio(),
                         zapatilla.getStock(),
                         zapatilla.getImagen(),
                         zapatilla.getAdministrador().getId(),
                         zapatilla.getModelo().getId(),
                         zapatilla.getCategoria().getId(),
                         zapatilla.getMarca().getId(),
                         zapatilla.getPersona().getId(),
                         baseUrl + "/file/" + zapatilla.getImagen()
                 ))
                .collect(Collectors.toList()) ;
    };

}
