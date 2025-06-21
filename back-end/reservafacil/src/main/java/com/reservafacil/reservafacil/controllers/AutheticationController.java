package com.reservafacil.reservafacil.controllers;

import com.reservafacil.reservafacil.DTO.AuthenticationDTO;
import com.reservafacil.reservafacil.DTO.LoginResponseDTO;
import com.reservafacil.reservafacil.DTO.UsuarioCadastroDTO;
import com.reservafacil.reservafacil.models.User;
import com.reservafacil.reservafacil.security.TokenService;
import com.reservafacil.reservafacil.services.AutheticationService;
import jakarta.validation.constraints.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.reservafacil.reservafacil.models.Role.funcionarios;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AutheticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AutheticationService service;
    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastro(@RequestBody UsuarioCadastroDTO usuarioCadastroDto) {
       User newUser = service.cadastrar(usuarioCadastroDto);
       if(newUser == null){
           return ResponseEntity.badRequest().body("Email ja cadastrado");
       }
        return ResponseEntity.ok().build();
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var authentication = authenticationManager.authenticate(usernamePassword);
        User user = (User) authentication.getPrincipal();
        String token = tokenService.generateToken(user);
        Long idUser = user.getId();
        String nome = user.getNome();
        return ResponseEntity.ok(new LoginResponseDTO(token,idUser,nome));
    }

}
