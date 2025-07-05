package com.reservafacil.reservafacil.controllers;

import com.reservafacil.reservafacil.dto.reserva.ReservaCadastroDTO;
import com.reservafacil.reservafacil.models.reservas.Reserva;
import com.reservafacil.reservafacil.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/reservas")
public class ReservaController {
    @Autowired
    private ReservaService service;

    @PostMapping("/cadastro")
    public ResponseEntity<Reserva> cadastro(@RequestBody ReservaCadastroDTO dto) throws Exception {
        Reserva reserva = service.cadastroReserva(dto);
        return  ResponseEntity.ok(reserva);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<Page<Reserva>> reservaPorId(@PathVariable("id") Long id, Pageable pageable){
        Page<Reserva> reservas =  service.reservasPorId(id,pageable);
        return  ResponseEntity.ok(reservas);
    }
}
