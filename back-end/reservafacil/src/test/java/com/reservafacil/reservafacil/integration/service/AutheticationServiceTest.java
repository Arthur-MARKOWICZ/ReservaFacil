package com.reservafacil.reservafacil.integration.service;

import com.reservafacil.reservafacil.dto.usuario.UsuarioCadastroDTO;
import com.reservafacil.reservafacil.models.user.Usuario;
import com.reservafacil.reservafacil.repositories.UsuarioRepository;
import com.reservafacil.reservafacil.services.AutheticationService;
import com.reservafacil.reservafacil.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AutheticationServiceTest  {
    @Mock
    private UsuarioRepository repository;
    @InjectMocks
    private AutheticationService service;
    @InjectMocks
    private UsuarioService userSevice;

    @Test
    void  criarUser_deveSalvarERetornarNome(){
        //dado
        String nome = "teste";
        UsuarioCadastroDTO dto = new UsuarioCadastroDTO(nome,"test@test","test");
        Usuario salvo = new Usuario(dto);
        when(repository.save(any(Usuario.class))).thenReturn(salvo);

        //quando
        Usuario resultado = service.cadastrar(dto);
        //entao
        assertNotNull(resultado.getId());
        assertEquals(nome,resultado.getNome());
        verify(repository,times(1)).save(any(Usuario.class));
    }

}
