package com.reservafacil.reservafacil.services;

import com.reservafacil.reservafacil.DTO.AlterarRoomDTO;
import com.reservafacil.reservafacil.DTO.RoomCadastroDTO;
import com.reservafacil.reservafacil.models.Room;
import com.reservafacil.reservafacil.repositories.RoomRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Slf4j
@Service
@Transactional
public class RoomService {
    @Autowired
    private RoomRepository repository;

    public Room cadastro(RoomCadastroDTO dto){
        log.info("Comecando o cadastro de sala");
        Room newRoom = new Room(dto);
        repository.save(newRoom);
        log.info("Cadastro de sala feito com sucesso");
        return newRoom;
    }
    public Page<Room> listar(Pageable pageable){
        Page<Room> todas = repository.findAll(pageable);
        return todas;
    }
    public  Room buscarPorId(Long id){
            return repository.findById(id).orElse(null);
    }
    public  Room atualizar(Long id, AlterarRoomDTO dto){
        log.info("Comecando o alterar dados da sala com id={}", id);
        Room room = this.buscarPorId(id);
        log.debug("Dados recebidos para alteração: {}", dto);
        room.alterarDados(dto);
        log.info("Dados alterados com sucesso");
        return repository.save(room);
    }

    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }
}

