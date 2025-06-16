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
    /**
     * Loads user details by email for authentication purposes.
     *
     * @param username the email address of the user to retrieve
     * @return the user details associated with the given email
     * @throws UsernameNotFoundException if no user is found with the specified email
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
    /**
     * Registers a new user if the email is not already in use.
     *
     * If a user with the provided email exists, returns {@code null}. Otherwise, encrypts the password, assigns the "funcionarios" role, saves the new user, and returns the created user.
     *
     * @param dto the data transfer object containing user registration information
     * @return the newly registered user, or {@code null} if the email is already registered
     */
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
