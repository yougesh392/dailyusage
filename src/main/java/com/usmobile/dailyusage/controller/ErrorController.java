package com.usmobile.dailyusage.controller;

import com.usmobile.dailyusage.utils.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;


/*
* Global exception controller
*
* */
@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiError> handleResponseStatusException(ResponseStatusException ex) {
        ApiError errorDetails = new ApiError(ex.getStatusCode().value(), ex.getReason());
        return new ResponseEntity<>(errorDetails, ex.getStatusCode());
    }
}
