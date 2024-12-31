package com.bernardino.desafio.services.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    
    public EmailAlreadyExistsException() {
        super("Email already exists");
    }

}
