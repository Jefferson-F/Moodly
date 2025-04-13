package com.moody.moody_api.exception;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("Usuario não encontrado");
    }
    public UserNotFoundException(String message){
        super(message);
    }
    
}
