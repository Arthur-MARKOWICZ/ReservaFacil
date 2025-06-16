package com.reservafacil.reservafacil.controllers;

import com.reservafacil.reservafacil.DTO.AuthenticationDTO;
import com.reservafacil.reservafacil.DTO.LoginResponseDTO;
import com.reservafacil.reservafacil.DTO.UsuarioCadastroDTO;
import com.reservafacil.reservafacil.models.User;
import com.reservafacil.reservafacil.repositories.UserRepository;
import com.reservafacil.reservafacil.security.TokenService;
import com.reservafacil.reservafacil.services.AutheticationService;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.reservafacil.reservafacil.models.Role.funcionarios;

@RestController
@RequestMapping("/auth")
public class AutheticationController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AutheticationService service;
    @PostMapping("/cadastro")
    public ResponseEntity cadastro(@RequestBody UsuarioCadastroDTO usuarioCadastroDto) {
       User newUser = service.cadastrar(usuarioCadastroDto);
       if(newUser == null){
           return ResponseEntity.badRequest().body("Email ja cadastrado");
       }
        return ResponseEntity.ok().build();
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO dto) {
        System.out.println("email: " + dto.email());
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var authentication = authenticationManager.authenticate(usernamePassword);
        var user = (User) authentication.getPrincipal();
        var token = tokenService.generateToken(user);
        Long idUser = user.getId();
        String nome = user.getNome();
        return ResponseEntity.ok(new LoginResponseDTO(token,idUser,nome));
    }

}
