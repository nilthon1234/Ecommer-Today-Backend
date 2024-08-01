package com.GrupoToday.DTO.modelsDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdministradorDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idAdminZapatillas;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String gmailAdministrador;

}
