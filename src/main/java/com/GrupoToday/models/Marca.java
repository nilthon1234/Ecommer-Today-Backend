package com.GrupoToday.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tb_marca")
public class Marca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@OneToMany(targetEntity = Zapatilla.class, mappedBy = "marca")
	private List<Zapatilla> zapatilla;
}
