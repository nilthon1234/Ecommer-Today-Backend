package com.GrupoToday.DTO.modelsDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CateDTO {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer idCategoria;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String nombreCategoria;

}
