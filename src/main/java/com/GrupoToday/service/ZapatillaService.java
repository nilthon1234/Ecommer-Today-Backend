package com.GrupoToday.service;

import com.GrupoToday.DTO.modelsDto.ZapatillasDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ZapatillaService {

    ZapatillasDto agregarZapatilla(ZapatillasDto zapatillaDto, MultipartFile file) throws IOException;
    ZapatillasDto obtenerZapatilla(Integer zapatillaId);
    List<ZapatillasDto> getAllZapatillas();
}
