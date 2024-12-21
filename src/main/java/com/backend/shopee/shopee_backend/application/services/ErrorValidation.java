package com.backend.shopee.shopee_backend.application.services;

public class ErrorValidation {
    public String Field;
    public String Message;

    public ErrorValidation(String field, String message){
        Field = field;
        Message = message;
    }

    public String getField() {
        return Field;
    }

    public String getMessage() {
        return Message;
    }
}