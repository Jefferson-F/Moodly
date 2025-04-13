package com.moody.moody_api.infra;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.moody.moody_api.exception.UserNotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundHandler(UserNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
    }
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> dataAcessExceptionHandler(DataAccessException exception){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Banco de dados indisponivel no momento.");
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> GenericErrorHandler(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado: "+ exception.getMessage());
    }
    
}
