package com.GrupoToday.models;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "tb_boleta")
public class Boleta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "num_boleta")
	private Integer id;
	
	@CreationTimestamp
	@JoinColumn(name = "fech_gene")
	private Date fechaGenerada;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

}
