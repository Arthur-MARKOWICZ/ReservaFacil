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

    public Room() {
    }
    public Room(RoomCadastroDTO dto){
        this.nome = dto.nome();
        this.localizacao = dto.localizacao();
        this.capacidade = dto.capacidade();
        this.temProjetor = dto.projetor();
    }
    public void alterarDados(RoomCadastroDTO dto){
        this.nome = dto.nome();
        this.localizacao = dto.localizacao();
        this.capacidade = dto.capacidade();
        this.temProjetor = dto.projetor();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public Boolean getTemProjetor() {
        return temProjetor;
    }

    public void setTemProjetor(Boolean temProjetor) {
        this.temProjetor = temProjetor;
    }
}
