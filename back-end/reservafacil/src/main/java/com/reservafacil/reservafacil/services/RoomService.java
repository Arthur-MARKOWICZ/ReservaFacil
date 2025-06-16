package com.reservafacil.reservafacil.services;

import com.reservafacil.reservafacil.DTO.RoomCadastroDTO;
import com.reservafacil.reservafacil.models.Room;
import com.reservafacil.reservafacil.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository repository;

    /**
     * Creates and saves a new Room entity using data from the provided DTO.
     *
     * @param dto the data transfer object containing information for the new Room
     * @return the newly created and persisted Room entity
     */
    public Room cadastro(RoomCadastroDTO dto){
        Room newRoom = new Room(dto);
        repository.save(newRoom);
        return newRoom;
    }
    /**
     * Retrieves a paginated list of all Room entities.
     *
     * @param pageable pagination and sorting information
     * @return a page containing Room entities
     */
    public Page<Room> listrar(Pageable pageable){
        Page<Room> todas = repository.findAll(pageable);
        return todas;
    }
    /****
     * Retrieves a Room entity by its ID.
     *
     * @param id the unique identifier of the Room
     * @return the Room entity if found, or null if not present
     */
    public  Room buscarPorId(Long id){
            return repository.findById(id).orElse(null);
    }
    /**
     * Updates an existing Room entity with new data from the provided DTO.
     *
     * @param id the ID of the Room to update
     * @param dto the data transfer object containing updated Room information
     * @return the updated Room entity, or null if the Room does not exist
     */
    public  Room atualizar(Long id,RoomCadastroDTO dto){
        Room room = this.buscarPorId(id);
        room.alterarDados(dto);
        return room;
    }

    /****
     * Deletes the Room entity with the specified ID from the repository.
     *
     * @param id the ID of the Room to delete
     */
    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }
}

