package com.GrupoToday.service;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.ZapatillasDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ZapatillaService {

    ZapatillasDto agregarZapatilla(ZapatillasDto zapatillaDto, MultipartFile file) throws IOException;
//    boolean actualizarZapatilla(Integer id, ZapatillasDto zapatillasDto, MultipartFile file) throws IOException;
    ZapatillasDto updateZapatilla(Integer id, ZapatillasDto zapatillasDto, MultipartFile file) throws IOException;
    CategoriaDTO detallsZapatilla(Integer idZapatilla);
    ZapatillasDto buscarIdZapatilla(Integer idZapatilla);
    void deleteZapatilla(Integer id);
    List<ZapatillasDto> getAllZapatillas();
}
