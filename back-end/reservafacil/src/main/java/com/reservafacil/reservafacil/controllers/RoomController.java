package com.reservafacil.reservafacil.controllers;

import com.reservafacil.reservafacil.DTO.AlterarRoomDTO;
import com.reservafacil.reservafacil.DTO.RoomCadastroDTO;
import com.reservafacil.reservafacil.models.Room;
import com.reservafacil.reservafacil.services.RoomService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService service;
    @PostMapping("/cadastro")
    public ResponseEntity<Room> cadastro(@RequestBody RoomCadastroDTO dto){
          Room newRoom =  service.cadastro(dto);
          return ResponseEntity.ok(newRoom);
    }
    @GetMapping("/todos")
    public ResponseEntity<Page<Room>> pegarTodos(Pageable pageable){
        Page<Room> rooms = service.listar(pageable);
        return ResponseEntity.ok(rooms);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Room> pegarPorId(@PathVariable("id") Long id){
        Room room = service.buscarPorId(id);
        return  ResponseEntity.ok(room);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Room> AtualizarPorId(@PathVariable("id") Long id,@RequestBody AlterarRoomDTO dto){
        Room room = service.atualizar(id,dto);
        return  ResponseEntity.ok(room);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeletarPorId(@PathVariable("id") Long id){
       service.deletarPorId(id);
        return  ResponseEntity.noContent().build();
    }
}
