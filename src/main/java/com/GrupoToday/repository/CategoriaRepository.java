package com.GrupoToday.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GrupoToday.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	Optional<Categoria> findByNombre(String nombre);

}
