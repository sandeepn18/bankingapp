package com.example.accounts.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class CustomerAlreadyExistsException extends RuntimeException{
    public CustomerAlreadyExistsException(String message){
        super(message);
    }
}
