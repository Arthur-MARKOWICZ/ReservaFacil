package com.reservafacil.reservafacil.services;

import com.reservafacil.reservafacil.models.User;
import com.reservafacil.reservafacil.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    private UserRepository repository;

    public User buscarPorID(Long id){
        User user = repository.getReferenceById(id);
        if(user == null){
            return null;
        }
        return user;
    }
}
