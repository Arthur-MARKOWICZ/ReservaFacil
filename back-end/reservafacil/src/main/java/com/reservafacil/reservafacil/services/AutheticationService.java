package com.reservafacil.reservafacil.services;

import com.reservafacil.reservafacil.dto.authentication.AuthenticationDTO;
import com.reservafacil.reservafacil.dto.authentication.LoginResponseDTO;
import com.reservafacil.reservafacil.dto.usuario.UsuarioCadastroDTO;
import com.reservafacil.reservafacil.models.user.Usuario;
import com.reservafacil.reservafacil.repositories.UsuarioRepository;
import com.reservafacil.reservafacil.security.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.reservafacil.reservafacil.models.user.Role.funcionarios;
@Slf4j
@Service
public class AutheticationService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutheticationService(UsuarioRepository userRepository,
                                @Lazy AuthenticationManager authenticationManager,
                                TokenService tokenService) {
        this.usuarioRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }
    public Usuario cadastrar(UsuarioCadastroDTO dto){
        log.info("Comecando o cadastro de usuario");
        Optional<Usuario> userCadastrado = usuarioRepository.findByEmail(dto.email());
        if(userCadastrado.isPresent()){
            return null;
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());
        Usuario newUser = new Usuario(dto);
        newUser.setSenha(encryptedPassword);
        newUser.setRole(funcionarios);
        this.usuarioRepository.save(newUser);
        log.info("Cadastro feito com sucesso");
        return newUser;
    }
    public LoginResponseDTO login(AuthenticationDTO dto){
        log.info("Comecando o login do usuario com email={}",dto.email());
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var authentication = authenticationManager.authenticate(usernamePassword);
        Usuario user = (Usuario) authentication.getPrincipal();
        String token = tokenService.generateToken(user);
        Long idUser = user.getId();
        String nome = user.getNome();
        log.info("Login feito com sucesso");
        return new LoginResponseDTO(token,idUser,nome);
    }
}
