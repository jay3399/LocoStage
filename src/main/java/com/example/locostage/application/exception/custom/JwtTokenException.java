package com.example.locostage.application.exception.custom;

public class JwtTokenException extends RuntimeException {

    public JwtTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
