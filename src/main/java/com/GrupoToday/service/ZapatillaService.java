package com.GrupoToday.service;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.DTO.modelsDto.ZapatillasDto;
import com.GrupoToday.models.Zapatilla;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ZapatillaService {

    ZapatillasDto agregarZapatilla(ZapatillasDto zapatillaDto, MultipartFile file) throws IOException;
    ZapatillasDto actualizarZapatilla(Integer id, ZapatillasDto zapatillasDto, MultipartFile file) throws IOException;
    CategoriaDTO detallsZapatilla(Integer idZapatilla);
    public String deleteZapatilla(Integer id) throws IOException;
    List<ZapatillasDto> getAllZapatillas();
}
