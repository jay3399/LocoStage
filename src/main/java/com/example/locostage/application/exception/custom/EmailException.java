package com.example.locostage.application.exception.custom;

public class EmailException extends RuntimeException {

    public EmailException(String message , Throwable cause) {
        super(message ,cause);
    }

}
