package com.example.bucard.model.exception;

public class PasswordNotCorrectException extends RuntimeException{
    public PasswordNotCorrectException(String message) {
        super(message);
    }
}
