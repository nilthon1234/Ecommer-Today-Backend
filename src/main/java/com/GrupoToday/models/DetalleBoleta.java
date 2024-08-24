package com.GrupoToday.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_detalle_boleta")
public class DetalleBoleta {
	
	@EmbeddedId
	private DetalleBoletaId id;
	
	@ManyToOne
	@MapsId("zapatillaId")
	@JoinColumn(name = "id_zapatilla")
	private Zapatilla zapatilla;
	
	@ManyToOne
	@MapsId("boletaId")
	@JoinColumn(name = "id_boleta")
	private Boleta boleta;
	private Double precio;
	private int cantidad;
	private Double subTotal;

}
