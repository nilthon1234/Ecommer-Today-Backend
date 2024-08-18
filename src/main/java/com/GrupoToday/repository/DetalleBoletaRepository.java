package com.GrupoToday.repository;

import com.GrupoToday.models.DetalleBoleta;
import com.GrupoToday.models.DetalleBoletaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleBoletaRepository extends JpaRepository<DetalleBoleta, DetalleBoletaId> {
}
