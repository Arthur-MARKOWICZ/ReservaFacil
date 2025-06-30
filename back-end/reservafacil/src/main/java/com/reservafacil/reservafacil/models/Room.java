package com.reservafacil.reservafacil.models;

import com.reservafacil.reservafacil.DTO.AlterarRoomDTO;
import com.reservafacil.reservafacil.DTO.RoomCadastroDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_Room")
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String localizacao;
    private int capacidade;
    private Boolean temProjetor;
    @Enumerated(EnumType.STRING)
    private Disponivel disponivel;

    public Room() {
    }
    public Room(RoomCadastroDTO dto){
        this.nome = dto.nome();
        this.localizacao = dto.localizacao();
        this.capacidade = dto.capacidade();
        this.temProjetor = dto.projetor();
        this.disponivel = disponivel;
    }
    public void alterarDados(AlterarRoomDTO dto){
        this.nome = dto.nome();
        this.localizacao = dto.localizacao();
        this.capacidade = dto.capacidade();
        this.temProjetor = dto.projetor();
        this.disponivel = dto.disponivel();

    }
}
