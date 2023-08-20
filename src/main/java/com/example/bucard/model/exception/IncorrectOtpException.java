package com.example.bucard.model.exception;

public class IncorrectOtpException extends RuntimeException {
    public IncorrectOtpException(String message) {
        super(message);
    }
}
