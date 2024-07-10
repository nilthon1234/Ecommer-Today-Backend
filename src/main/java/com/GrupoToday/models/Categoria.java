package com.GrupoToday.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	@OneToMany(targetEntity = Zapatilla.class ,mappedBy = "categoria")
	private List<Zapatilla> zapatilla;
}
