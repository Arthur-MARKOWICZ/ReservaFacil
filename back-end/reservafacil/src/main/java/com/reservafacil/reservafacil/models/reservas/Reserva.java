package com.reservafacil.reservafacil.models.reservas;

import com.reservafacil.reservafacil.dto.reserva.ReservaCadastroDTO;
import com.reservafacil.reservafacil.models.room.Sala;
import com.reservafacil.reservafacil.models.user.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_reserva")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime horarioInicial;
    private LocalDateTime horarioFinal;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Sala room;

    public Reserva(ReservaCadastroDTO dto, Sala room, Usuario usuario) {
        this.horarioInicial = dto.horarioInicial();
        this.horarioFinal = dto.horarioFinal();
        this.room = room;
        this.usuario = usuario;
    }
}
