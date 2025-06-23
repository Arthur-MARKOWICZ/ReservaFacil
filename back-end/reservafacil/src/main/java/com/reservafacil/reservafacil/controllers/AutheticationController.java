package com.reservafacil.reservafacil.controllers;

import com.reservafacil.reservafacil.DTO.AuthenticationDTO;
import com.reservafacil.reservafacil.DTO.LoginResponseDTO;
import com.reservafacil.reservafacil.DTO.UsuarioCadastroDTO;
import com.reservafacil.reservafacil.models.User;
import com.reservafacil.reservafacil.services.AutheticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/auth")
public class AutheticationController {
    private final AutheticationService service;

    public AutheticationController(AutheticationService service) {
        this.service = service;
    }
    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastro(@RequestBody UsuarioCadastroDTO usuarioCadastroDto) {
       User newUser = service.cadastrar(usuarioCadastroDto);
       if(newUser == null){
           return ResponseEntity.badRequest().body("Email ja cadastrado");
       }
        return ResponseEntity.ok("Usuario cadastrado com sucesso");
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO dto) {
         LoginResponseDTO loginResponseDTO = service.login(dto);
        return ResponseEntity.ok(loginResponseDTO);
    }

}
