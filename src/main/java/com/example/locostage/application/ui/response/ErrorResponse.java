package com.example.locostage.application.ui.response;

public class ErrorResponse {

    private int status;

    private String error;

    private String message;

    private long timestamp;



    public static ErrorResponse create(int status , String error , String message , long timestamp) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.status = status;
        errorResponse.error = error;
        errorResponse.message = message;
        errorResponse.timestamp = timestamp;

        return errorResponse;
    }



}
