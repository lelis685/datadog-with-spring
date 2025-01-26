package com.example.datadog.controller.exception;

public class InvalidCardException extends RuntimeException{

    public InvalidCardException(String message) {
        super(message);
    }
}
