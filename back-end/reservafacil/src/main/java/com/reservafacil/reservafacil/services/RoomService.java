package com.reservafacil.reservafacil.services;

import com.reservafacil.reservafacil.DTO.RoomCadastroDTO;
import com.reservafacil.reservafacil.models.Room;
import com.reservafacil.reservafacil.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoomService {
    @Autowired
    private RoomRepository repository;

    public Room cadastro(RoomCadastroDTO dto){
        Room newRoom = new Room(dto);
        repository.save(newRoom);
        return newRoom;
    }
    public Page<Room> listar(Pageable pageable){
        Page<Room> todas = repository.findAll(pageable);
        return todas;
    }
    public  Room buscarPorId(Long id){
            return repository.findById(id).orElse(null);
    }
    public  Room atualizar(Long id,RoomCadastroDTO dto){
        Room room = this.buscarPorId(id);
        room.alterarDados(dto);
        return repository.save(room);
    }

    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }
}

