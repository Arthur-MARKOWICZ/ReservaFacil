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
    /**
     * Creates a new Room entity using the provided data.
     *
     * @param dto the data transfer object containing information for the new Room
     * @return a ResponseEntity containing the created Room and HTTP 200 OK status
     */
    @PostMapping("/cadastro")
    public ResponseEntity cadastro(@RequestBody RoomCadastroDTO dto){
          Room newRoom =  service.cadastro(dto);
          return ResponseEntity.ok(newRoom);
    }
    /**
     * Retrieves a Room entity by its unique identifier.
     *
     * @param id the unique identifier of the Room to retrieve
     * @return a ResponseEntity containing the Room if found, with HTTP 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<Room> pegarPorId(@PathParam("id") Long id){
        Room room = service.buscarPorId(id);
        return  ResponseEntity.ok(room);
    }
    /**
     * Updates an existing Room identified by its ID with new data.
     *
     * @param id the ID of the Room to update
     * @param dto the data to update the Room with
     * @return a ResponseEntity containing the updated Room and HTTP 200 OK status
     */
    @PutMapping("/{id}")
    public ResponseEntity<Room> AtualizarPorId(@PathParam("id") Long id,@RequestBody RoomCadastroDTO dto){
        Room room = service.atualizar(id,dto);
        return  ResponseEntity.ok(room);
    }
    /****
     * Deletes the Room entity identified by the given ID.
     *
     * @param id the ID of the Room to delete
     * @return a ResponseEntity with HTTP 204 No Content if deletion is successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity DeletarPorId(@PathParam("id") Long id){
       service.deletarPorId(id);
        return  ResponseEntity.noContent().build();
    }
}
