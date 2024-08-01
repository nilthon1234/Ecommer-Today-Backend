package com.GrupoToday.DTO.modelsDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModeloDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idModelo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombreModelo;
}
