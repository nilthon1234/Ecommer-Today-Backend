package com.GrupoToday.repository;

import com.GrupoToday.models.Zapatilla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZapatillaRepository extends JpaRepository<Zapatilla, Integer> {

}
