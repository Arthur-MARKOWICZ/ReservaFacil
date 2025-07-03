package com.reservafacil.reservafacil.services;

import com.reservafacil.reservafacil.dto.reserva.ReservaCadastroDTO;
import com.reservafacil.reservafacil.models.reservas.Reserva;
import com.reservafacil.reservafacil.models.room.Sala;
import com.reservafacil.reservafacil.models.user.Usuario;
import com.reservafacil.reservafacil.repositories.ReservaRepository;

import com.reservafacil.reservafacil.repositories.SalaRepository;
import com.reservafacil.reservafacil.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository respository;
    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    public Reserva cadastroReserva(ReservaCadastroDTO dto) throws Exception {
        List<Reserva> reservaExistente = respository.findReservasConflitantes(dto.id_room(), dto.horarioInicial(),
                dto.horarioFinal());
        if (!reservaExistente.isEmpty()){
            throw new Exception("Ja existe reserva este horario para esta sala");
        }
        Sala room = salaRepository.findById(dto.id_room()).
                orElseThrow(()-> new RuntimeException("Sala nao encontrada"));
        Usuario usuario = usuarioRepository.findById(dto.id_usuario()).
                orElseThrow(()-> new RuntimeException("Usuario nao encontrada"));
        Reserva novaReserva = new Reserva(dto,room,usuario);

        return respository.save(novaReserva);
    }
}
