package com.reservafacil.reservafacil.exception.global;

import com.reservafacil.reservafacil.exception.reserva.ReservaConflitoException;
import com.reservafacil.reservafacil.exception.sala.SalaJaCadastrada;
import com.reservafacil.reservafacil.exception.sala.SalaNaoEncontradaException;
import com.reservafacil.reservafacil.exception.usuario.UsuarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExecptionHandler {
    @ExceptionHandler(ReservaConflitoException.class)
    public ResponseEntity<Object> handleReservaConflito(ReservaConflitoException ex){
        return buildResponse(HttpStatus.CONFLICT,ex.getMessage());
    }
    @ExceptionHandler(SalaNaoEncontradaException.class)
    public ResponseEntity<Object> handleSalaNaoEncontrada(SalaNaoEncontradaException ex){
        return  buildResponse(HttpStatus.NOT_FOUND,ex.getMessage());
    }
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<Object> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex){
        return  buildResponse(HttpStatus.NOT_FOUND,ex.getMessage());
    }
    @ExceptionHandler(SalaJaCadastrada.class)
    public  ResponseEntity<Object> handleSalaJaCadastrada(SalaJaCadastrada ex){
        return  buildResponse(HttpStatus.CONFLICT,ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneric(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado: " + ex.getMessage());
    }



    private ResponseEntity<Object> buildResponse(HttpStatus status, String messagem){
        Map<String,Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status",status.value());
        body.put("error", status.getReasonPhrase());
        body.put("messagem", messagem);
        return new ResponseEntity<>(body,status);
    }
}
