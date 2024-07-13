package com.GrupoToday.models;

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

	@OneToMany(targetEntity = Zapatilla.class, mappedBy = "marca")
	private List<Zapatilla> zapatilla;
}
