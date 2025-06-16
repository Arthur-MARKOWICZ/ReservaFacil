package com.reservafacil.reservafacil.controllers;

import com.reservafacil.reservafacil.DTO.RoomCadastroDTO;
import com.reservafacil.reservafacil.models.Room;
import com.reservafacil.reservafacil.services.RoomService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService service;
    @PostMapping("/cadastro")
    public ResponseEntity cadastro(@RequestBody RoomCadastroDTO dto){
          Room newRoom =  service.cadastro(dto);
          return ResponseEntity.ok(newRoom);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Room> pegarPorId(@PathParam("id") Long id){
        Room room = service.buscarPorId(id);
        return  ResponseEntity.ok(room);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Room> AtualizarPorId(@PathParam("id") Long id,@RequestBody RoomCadastroDTO dto){
        Room room = service.atualizar(id,dto);
        return  ResponseEntity.ok(room);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity DeletarPorId(@PathParam("id") Long id){
       service.deletarPorId(id);
        return  ResponseEntity.noContent().build();
    }
}
