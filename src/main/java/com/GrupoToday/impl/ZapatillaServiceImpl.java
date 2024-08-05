package com.GrupoToday.impl;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.ZapatillasDto;
import com.GrupoToday.DTO.response.BusquedaId;
import com.GrupoToday.impl.mapper.ZapatillaMapper;
import com.GrupoToday.models.*;
import com.GrupoToday.repository.ZapatillaRepository;
import com.GrupoToday.service.FileService;
import com.GrupoToday.service.ZapatillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
            null,
            zapatillaGuardada.getModelo().getId(),
            null,
            zapatillaGuardada.getCategoria().getId(),
            null,
            zapatillaGuardada.getMarca().getId(),
            null,
            zapatillaGuardada.getPersona().getId(),
            null,
            urlImg
    );

        return response;
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

        // 3. Mapear DTO a entidad Zapatilla
        Administrador administrador = new Administrador();
        administrador.setId(zapatillasDto.getIdAdminZapatillas());
        Modelo modelo = new Modelo();
        modelo.setId(zapatillasDto.getIdModeloZapatilla());
        Categoria categoria = new Categoria();
        categoria.setId(zapatillasDto.getIdCategoriaZapatilla());
        Marca marca = new Marca();
        marca.setId(zapatillasDto.getIdMarcaZapatilla());
        Persona persona = new Persona();
        persona.setId(zapatillasDto.getIdPersonaZapatilla());

        Zapatilla zapa = new Zapatilla(
                zapatilla.getId(),
                zapatillasDto.getNombreZapatilla(),
                zapatillasDto.getDescripcionZapatilla(),
                zapatillasDto.getPrecioZapatilla(),
                zapatillasDto.getStockZapatilla(),
                zapatillasDto.getImagenZapatilla(),
                administrador,
                modelo,
                categoria,
                marca,
                persona
        );

        // 4. Guardar la zapatilla actualizada
        Zapatilla update = zapatillaRepository.save(zapa);

        // 5. Generar la URL de la imagen
        String urlImg = baseUrl + "/file/" + update.getImagen();

        // 6. Devolver el DTO actualizado
        ZapatillasDto response = new ZapatillasDto(
                update.getId(),
                update.getNombre(),
                update.getDescripcion(),
                update.getPrecio(),
                update.getStock(),
                update.getImagen(),
                update.getAdministrador().getId(),
                null,
                update.getModelo().getId(),
                null,
                update.getCategoria().getId(),
                null,
                update.getMarca().getId(),
                null,
                update.getPersona().getId(),
                null,
                urlImg
        );

        return response;
    }

//    @Override
//    public boolean actualizarZapatilla(Integer id, ZapatillasDto zapatillasDto, MultipartFile file) throws IOException {
//        try {
//            Zapatilla zapa = zapatillaRepository.findById(id).orElseThrow(() -> new RuntimeException("Zapatilla no encontrada"));
//            String nombreArchivo = zapa.getImagen();
//
//            if (file != null && !file.isEmpty()) {
//                // Eliminar el archivo existente si existe y subir el nuevo archivo
//                if (nombreArchivo != null) {
//                    Files.deleteIfExists(Paths.get(path + File.separator + nombreArchivo));
//                }
//                nombreArchivo = fileService.subirArchivo(path, file);
//            }
//
//            zapatillasDto.setImagenZapatilla(nombreArchivo);
//
//            // Actualizar la entidad Zapatilla con los datos del DTO
//            Zapatilla zapatilla = new Zapatilla(
//                    zapa.getId(),
//                    zapatillasDto.getNombreZapatilla(),
//                    zapatillasDto.getDescripcionZapatilla(),
//                    zapatillasDto.getPrecioZapatilla(),
//                    zapatillasDto.getStockZapatilla(),
//                    zapatillasDto.getImagenZapatilla(),
//                    zapa.getAdministrador(),
//                    zapa.getModelo(),
//                    zapa.getCategoria(),
//                    zapa.getMarca(),
//                    zapa.getPersona()
//            );
//
//            // Guardar la zapatilla actualizada en la base de datos
//            zapatillaRepository.save(zapatilla);
//            return true;
//
//        } catch (RuntimeException e) {
//            return false;
//        }
//    }

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
    public String deleteZapatilla(Integer id) throws IOException {
        Zapatilla mv = zapatillaRepository.findById(id).orElseThrow(() -> new RuntimeException("no encontrado id"));
        Integer myTipo = mv.getId();
        //2. delete the file associated with this object
        Files.deleteIfExists(Paths.get(path + File.separator + mv.getImagen()));
        zapatillaRepository.delete(mv);
        return "Zapatilla con id " + myTipo + " eliminada";

    }


//    @Override
//    public void deleteZapatilla(Integer id) {
//        Optional<Zapatilla> siEncontro = zapatillaRepository.findById(id);
//        if (siEncontro.isPresent()) {
//            Zapatilla zp = siEncontro.get(); // Obtenemos la zapatilla encontrada
//            try {
//                Files.deleteIfExists(Paths.get(path, zp.getImagen()));
//                zapatillaRepository.deleteById(id); // Eliminamos la zapatilla de la base de datos
//            } catch (IOException e) {
//                // Manejamos la excepción apropiadamente (por ejemplo, imprimiendo un mensaje de error)
//                e.printStackTrace();
//            }
//        } else {
//            throw new IllegalArgumentException("Zapatilla no encontrada");
//        }
//    }

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
