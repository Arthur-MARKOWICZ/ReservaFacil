package com.reservafacil.reservafacil.controllers;

import com.reservafacil.reservafacil.dto.sala.SalaAlterarDTO;
import com.reservafacil.reservafacil.dto.sala.SalaCadastroDTO;
import com.reservafacil.reservafacil.models.room.Sala;
import com.reservafacil.reservafacil.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/room")
public class SalaController {
    @Autowired
    private RoomService service;
    @PostMapping("/cadastro")
    public ResponseEntity<Sala> cadastro(@RequestBody SalaCadastroDTO dto){
          Sala newRoom =  service.cadastro(dto);
          return ResponseEntity.ok(newRoom);
    }
    @GetMapping("/todos")
    public ResponseEntity<Page<Sala>> pegarTodos(Pageable pageable){
        Page<Sala> rooms = service.listar(pageable);
        return ResponseEntity.ok(rooms);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Sala> pegarPorId(@PathVariable("id") Long id){
        Sala room = service.buscarPorId(id);
        return  ResponseEntity.ok(room);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Sala> AtualizarPorId(@PathVariable("id") Long id, @RequestBody SalaAlterarDTO dto){
        Sala room = service.atualizar(id,dto);
        return  ResponseEntity.ok(room);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeletarPorId(@PathVariable("id") Long id){
       service.deletarPorId(id);
        return  ResponseEntity.noContent().build();
    }
}
