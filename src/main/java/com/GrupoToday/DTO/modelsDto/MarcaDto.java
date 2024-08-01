package com.GrupoToday.DTO.modelsDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MarcaDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idMaraca;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombreMarca;
}
