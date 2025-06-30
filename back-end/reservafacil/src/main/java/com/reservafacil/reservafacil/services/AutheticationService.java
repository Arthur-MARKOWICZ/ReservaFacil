package com.reservafacil.reservafacil.services;

import com.reservafacil.reservafacil.DTO.AuthenticationDTO;
import com.reservafacil.reservafacil.DTO.LoginResponseDTO;
import com.reservafacil.reservafacil.DTO.UsuarioCadastroDTO;
import com.reservafacil.reservafacil.models.User;
import com.reservafacil.reservafacil.repositories.UserRepository;
import com.reservafacil.reservafacil.security.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.reservafacil.reservafacil.models.Role.funcionarios;
@Slf4j
@Service
public class AutheticationService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutheticationService(UserRepository userRepository,
                                @Lazy AuthenticationManager authenticationManager,
                                TokenService tokenService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
    public User cadastrar(UsuarioCadastroDTO dto){
        log.info("Comecando o cadastro de usuario");
        Optional<User> userCadastrado = userRepository.findByEmail(dto.email());
        if(userCadastrado.isPresent()){
            return null;
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());
        User newUser = new User(dto);
        newUser.setSenha(encryptedPassword);
        newUser.setRole(funcionarios);
        this.userRepository.save(newUser);
        log.info("Cadastro feito com sucesso");
        return newUser;
    }
    public LoginResponseDTO login(AuthenticationDTO dto){
        log.info("Comecando o login do usuario com email={}",dto.email());
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var authentication = authenticationManager.authenticate(usernamePassword);
        User user = (User) authentication.getPrincipal();
        String token = tokenService.generateToken(user);
        Long idUser = user.getId();
        String nome = user.getNome();
        log.info("Login feito com sucesso");
        return new LoginResponseDTO(token,idUser,nome);
    }
}
