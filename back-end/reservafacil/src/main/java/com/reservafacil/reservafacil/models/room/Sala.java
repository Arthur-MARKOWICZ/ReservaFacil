package com.reservafacil.reservafacil.models.room;

import com.reservafacil.reservafacil.dto.sala.SalaAlterarDTO;
import com.reservafacil.reservafacil.dto.sala.SalaCadastroDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_Sala")
@Getter
@Setter
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String localizacao;
    private int capacidade;
    private Boolean temProjetor;
    @Enumerated(EnumType.STRING)
    private Disponivel disponivel;

    public Sala() {
    }
    public Sala(SalaCadastroDTO dto){
        this.nome = dto.nome();
        this.localizacao = dto.localizacao();
        this.capacidade = dto.capacidade();
        this.temProjetor = dto.projetor();
        this.disponivel = disponivel;
    }
    public void alterarDados(SalaAlterarDTO dto){
        this.nome = dto.nome();
        this.localizacao = dto.localizacao();
        this.capacidade = dto.capacidade();
        this.temProjetor = dto.projetor();
        this.disponivel = dto.disponivel();

    }
}
