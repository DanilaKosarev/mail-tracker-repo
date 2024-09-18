package com.example.mail_tracker.controllers;

import com.example.mail_tracker.exceptions.NoSuchPostOfficeException;
import com.example.mail_tracker.exceptions.NoSuchPostalItemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));

        return errorMap;
    }

    @ExceptionHandler({NoSuchPostOfficeException.class, NoSuchPostalItemException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFoundException(RuntimeException e) {
        return Map.of("id", e.getMessage());
    }

}
