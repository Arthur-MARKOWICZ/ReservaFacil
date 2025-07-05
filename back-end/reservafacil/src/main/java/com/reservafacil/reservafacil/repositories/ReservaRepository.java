package com.reservafacil.reservafacil.repositories;

import com.reservafacil.reservafacil.models.reservas.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {
    @Query("""
                SELECT r FROM Reserva r
                WHERE r.room.id = :id_room AND
                      (:horarioInicial < r.horarioFinal AND :horarioFinal > r.horarioInicial)
            """)
    List<Reserva> findReservasConflitantes(@Param("id_room") Long id_room,
                                           @Param("horarioInicial") LocalDateTime horarioInicial,
                                           @Param("horarioFinal") LocalDateTime horarioFinal);
    @Query("SELECT r FROM Reserva r WHERE r.id_usuario_id = :id")
    Page<Reserva> findPorId(@Param("id") Long id, Pageable pageable);
}
