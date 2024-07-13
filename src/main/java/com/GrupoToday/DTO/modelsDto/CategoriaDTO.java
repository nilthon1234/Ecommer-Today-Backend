package com.GrupoToday.DTO.modelsDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombreCategoria;
    private String nombreZapatilla;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String marcaZapatilla;
    private String descriptionZapatilla;
    private String stockZapatilla;
    private String precioZapatilla;

}
