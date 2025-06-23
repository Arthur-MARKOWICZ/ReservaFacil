package com.reservafacil.reservafacil.integration.repositories;

import com.reservafacil.reservafacil.DTO.UsuarioCadastroDTO;
import com.reservafacil.reservafacil.models.User;
import com.reservafacil.reservafacil.repositories.UserRepository;
import com.reservafacil.reservafacil.services.AutheticationService;
import com.reservafacil.reservafacil.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {
    @Mock
    private UserRepository repository;
    @InjectMocks
    private AutheticationService service;
    @InjectMocks
    private UserService userSevice;

    @Test
    void  criarUser_deveSalvarERetornarNome(){
        //dado
        String nome = "teste";
        UsuarioCadastroDTO dto = new UsuarioCadastroDTO(nome,"test@test","test");
        User salvo = new User(dto);
        when(repository.save(any(User.class))).thenReturn(salvo);

        //quando
        User resultado = service.cadastrar(dto);
        //entao
        assertNotNull(resultado.getId());
        assertEquals(nome,resultado.getNome());
        verify(repository,times(1)).save(any(User.class));
    }

}
