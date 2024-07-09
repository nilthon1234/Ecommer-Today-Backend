package com.GrupoToday.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class DetalleBoletaId implements Serializable{
	
	private Integer zapatillaId;
	private Integer boletaId;
}
