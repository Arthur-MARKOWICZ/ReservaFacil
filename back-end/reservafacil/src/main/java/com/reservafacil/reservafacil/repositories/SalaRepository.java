package com.reservafacil.reservafacil.repositories;

import com.reservafacil.reservafacil.models.room.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala,Long> {
}
