package com.GrupoToday.repository;

import com.GrupoToday.models.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarcaReposity extends JpaRepository<Marca, Integer> {
    Optional<Marca> findByNombre(String nombre);
}
