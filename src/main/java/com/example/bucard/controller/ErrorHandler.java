package com.example.bucard.controller;

import com.example.bucard.model.exception.ExceptionResponse;
import com.example.bucard.model.exception.NotFoundException;
import com.example.bucard.model.exception.PasswordNotCorrectException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handle(NotFoundException exception){
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(PasswordNotCorrectException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse handle(PasswordNotCorrectException exception){
        return new ExceptionResponse(exception.getMessage());
    }
}
