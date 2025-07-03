package com.reservafacil.reservafacil.dto.sala;

import com.reservafacil.reservafacil.models.room.Disponivel;

public record SalaAlterarDTO(String nome, String localizacao, int capacidade, boolean projetor, Disponivel disponivel) {
}
