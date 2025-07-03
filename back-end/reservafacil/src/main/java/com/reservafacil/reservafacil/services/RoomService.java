package com.reservafacil.reservafacil.services;

import com.reservafacil.reservafacil.dto.sala.SalaAlterarDTO;
import com.reservafacil.reservafacil.dto.sala.SalaCadastroDTO;
import com.reservafacil.reservafacil.exception.sala.SalaJaCadastrada;
import com.reservafacil.reservafacil.models.room.Sala;
import com.reservafacil.reservafacil.repositories.SalaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class RoomService {
    @Autowired
    private SalaRepository repository;

    public Sala cadastro(SalaCadastroDTO dto){
        log.info("Comecando o cadastro de sala");
        Sala newRoom = new Sala(dto);
        try{
            repository.save(newRoom);
        } catch (RuntimeException e) {
            throw new SalaJaCadastrada("Sala ja cadastrada");
        }

        log.info("Cadastro de sala feito com sucesso");
        return newRoom;
    }
    public Page<Sala> listar(Pageable pageable){
        Page<Sala> todas = repository.findAll(pageable);
        return todas;
    }
    public Sala buscarPorId(Long id){
            return repository.findById(id).orElse(null);
    }
    public Sala atualizar(Long id, SalaAlterarDTO dto){
        log.info("Comecando o alterar dados da sala com id={}", id);
        Sala room = this.buscarPorId(id);
        log.debug("Dados recebidos para alteração: {}", dto);
        room.alterarDados(dto);
        log.info("Dados alterados com sucesso");
        return repository.save(room);
    }

    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }
}

