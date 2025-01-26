package com.example.datadog.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(InvalidCardException.class)
    public ProblemDetail handleInvalidInputException(InvalidCardException e, WebRequest request) {
        ProblemDetail problemDetail
                = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setInstance(URI.create("cards"));
        return problemDetail;
    }


}
