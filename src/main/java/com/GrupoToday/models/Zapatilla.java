package com.GrupoToday.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "tb_zapatilla")
public class Zapatilla {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcion;
	private String precio;
	private String stock;
	private String imagen;
	
	@ManyToOne
	@JoinColumn(name = "id_admin")
	private Administrador administrador;
	
	@ManyToOne
	@JoinColumn(name = "id_modelo")
	private Modelo modelo;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "id_marca")
	private Marca marca;

	@ManyToOne
	@JoinColumn(name = "id_persona")
	private Persona persona;
	

}
