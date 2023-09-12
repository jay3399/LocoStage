package com.example.locostage.application.ui.response;

public class ErrorResponse {

    private int status;

    private String error;

    private long timestamp;



    public static ErrorResponse create(int status , String error  , long timestamp) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.status = status;
        errorResponse.error = error;
        errorResponse.timestamp = timestamp;

        return errorResponse;
    }



}
