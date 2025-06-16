package com.reservafacil.reservafacil.models;

import com.reservafacil.reservafacil.DTO.RoomCadastroDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String localizacao;
    private int capacidade;
    private Boolean temProjetor;

    /**
     * Creates a new Room instance with default values.
     */
    public Room() {
    }
    /**
     * Constructs a Room entity by initializing its fields from the provided RoomCadastroDTO.
     *
     * @param dto the data transfer object containing room details
     */
    public Room(RoomCadastroDTO dto){
        this.nome = dto.nome();
        this.localizacao = dto.localizacao();
        this.capacidade = dto.capacidade();
        this.temProjetor = dto.projetor();
    }
    /****
     * Updates the room's attributes with values from the provided RoomCadastroDTO.
     *
     * @param dto the data transfer object containing updated room information
     */
    public void alterarDados(RoomCadastroDTO dto){
        this.nome = dto.nome();
        this.localizacao = dto.localizacao();
        this.capacidade = dto.capacidade();
        this.temProjetor = dto.projetor();
    }
    /**
     * Returns the unique identifier of the room.
     *
     * @return the room's ID
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the room.
     *
     * @param id the room's unique identifier
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns the name of the room.
     *
     * @return the room's name
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the name of the room.
     *
     * @param nome the new name for the room
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Returns the location of the room.
     *
     * @return the room's location
     */
    public String getLocalizacao() {
        return localizacao;
    }

    /**
     * Sets the location of the room.
     *
     * @param localizacao the new location to assign to the room
     */
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * Returns the capacity of the room.
     *
     * @return the number of people the room can accommodate
     */
    public int getCapacidade() {
        return capacidade;
    }

    /**
     * Sets the capacity of the room.
     *
     * @param capacidade the new capacity value
     */
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    /**
     * Returns whether the room has a projector.
     *
     * @return true if the room has a projector, false otherwise
     */
    public Boolean getTemProjetor() {
        return temProjetor;
    }

    /**
     * Sets whether the room has a projector.
     *
     * @param temProjetor true if the room has a projector, false otherwise
     */
    public void setTemProjetor(Boolean temProjetor) {
        this.temProjetor = temProjetor;
    }
}
