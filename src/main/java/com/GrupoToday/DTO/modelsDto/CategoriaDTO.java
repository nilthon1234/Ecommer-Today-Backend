package com.GrupoToday.DTO.modelsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {
    private String nombreCategoria;
    private String nombreZapatilla;
    private String marcaZapatilla;
    private String descriptionZapatilla;
    private String stockZapatilla;
    private String precioZapatilla;

}
