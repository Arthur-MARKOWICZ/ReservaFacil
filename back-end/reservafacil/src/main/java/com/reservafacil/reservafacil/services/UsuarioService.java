package com.reservafacil.reservafacil.services;

import com.reservafacil.reservafacil.models.user.Usuario;
import com.reservafacil.reservafacil.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario buscarPorID(Long id){
        try {
         return repository.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            return null;
        }

    }
}
