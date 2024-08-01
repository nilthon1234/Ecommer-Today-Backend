package com.GrupoToday.DTO.modelsDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonaDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idPersona;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nombrePersona;
}
