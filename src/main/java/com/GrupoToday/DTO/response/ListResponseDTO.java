package com.GrupoToday.DTO.response;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.models.Categoria;
import lombok.Data;

import java.util.List;

@Data
public class ListResponseDTO {
    private List<Categoria> categoriaList;
    private List<CategoriaDTO> zapatillaList;

}
