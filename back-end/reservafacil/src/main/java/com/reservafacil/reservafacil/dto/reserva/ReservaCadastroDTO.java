package com.reservafacil.reservafacil.dto.reserva;

import java.time.LocalDateTime;

public record ReservaCadastroDTO(Long id_usuario, Long id_room, LocalDateTime horarioInicial,LocalDateTime horarioFinal) {
}
