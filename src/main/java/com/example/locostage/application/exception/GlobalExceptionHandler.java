package com.example.locostage.application.exception;

import com.example.locostage.application.exception.custom.EmailException;
import com.example.locostage.application.exception.custom.JwtTokenException;
import com.example.locostage.application.ui.response.ErrorResponse;
import io.jsonwebtoken.JwtException;
import jakarta.persistence.EntityNotFoundException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {

        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {

        return createErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(ExecutionException.class)
    public ResponseEntity<ErrorResponse> handleExecutionException(ExecutionException e) {

        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

    }

    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<ErrorResponse> handleInterruptedException(InterruptedException e) {
        Thread.currentThread().interrupt();  // 현재 스레드의 인터럽트 상태를 다시 설정  
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ErrorResponse> handleTimeoutException(TimeoutException e) {

        return createErrorResponse(HttpStatus.REQUEST_TIMEOUT, e.getMessage());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handleJwtException(JwtTokenException e) {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEmailException(EmailException e) {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }







    private ResponseEntity<ErrorResponse> createErrorResponse(HttpStatus status, String message) {

        ErrorResponse errorResponse = ErrorResponse.create(status.value(), message,
                System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, status);
    }


}
