package com.reservafacil.reservafacil.DTO;

import com.reservafacil.reservafacil.models.Disponivel;

public record AlterarRoomDTO(String nome, String localizacao, int capacidade, boolean projetor, Disponivel disponivel) {
}
