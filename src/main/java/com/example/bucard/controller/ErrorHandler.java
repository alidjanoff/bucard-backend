package com.example.bucard.controller;

import com.example.bucard.model.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handle(NotFoundException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(PasswordNotCorrectException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse handle(PasswordNotCorrectException exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(IncorrectOtpException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handle(IncorrectOtpException exception){
        return new ExceptionResponse(exception.getMessage());
    }
    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handle(AlreadyExistException exception){
        return new ExceptionResponse(exception.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ExceptionResponse> handle(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors().stream()
            .map(e -> new ExceptionResponse(e.getDefaultMessage()))
            .collect(Collectors.toList());
    }

}
