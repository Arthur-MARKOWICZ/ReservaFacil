package com.reservafacil.reservafacil.services;

import com.reservafacil.reservafacil.DTO.UsuarioCadastroDTO;
import com.reservafacil.reservafacil.models.User;
import com.reservafacil.reservafacil.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.reservafacil.reservafacil.models.Role.funcionarios;

@Service
public class AutheticationService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
    public User cadastrar(UsuarioCadastroDTO dto){
        Optional<User> userCadastrado = userRepository.findByEmail(dto.email());
        if(userCadastrado.isPresent()){
            return null;
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());
        User newUser = new User(dto);
        newUser.setSenha(encryptedPassword);
        newUser.setRole(funcionarios);
        this.userRepository.save(newUser);
        return newUser;
    }
}
