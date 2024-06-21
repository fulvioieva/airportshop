package com.mr.Airport.apiresponse;

public class ApiResponse<T> {
    private T response;
    private String message;
    private int status;

    public ApiResponse() {
    }

    public ApiResponse(T response, String message, int status) {
        this.response = response;
        this.message = message;
        this.status = status;
    }

    // Getters and Setters
    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

