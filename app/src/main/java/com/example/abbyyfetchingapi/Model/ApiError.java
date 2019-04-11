package com.example.abbyyfetchingapi.Model;

public class ApiError {
    private int statusCode;
    private String endpoint;
    private String message = "Unknown error.";

    public int getStatusCode() {
        return statusCode;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getMessage() {
        return message;
    }
}
