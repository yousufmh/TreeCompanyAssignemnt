package com.example.TreeAssigmnet.Model;

public class SessionStatus {

    private int statusCode;
    private String Message;

    public SessionStatus(int statusCode, String message) {

        this.statusCode = statusCode;
        Message = message;
    }

    public SessionStatus() {

        this.statusCode = 0;
        Message = "";
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
