package com.example.demo.presentation.exception;

import com.example.demo.core.exception.EmailAlreadyExistsException;
import com.example.demo.core.exception.ResourceNotFoundException;
import com.example.demo.presentation.controller.DeveloperController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice(assignableTypes = DeveloperController.class)
public class DeveloperExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        List<String> errors = List.of("Email is already registered.", "Please use a different email address.");
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), errors);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleEmailAlreadyExistsException(ResourceNotFoundException ex) {
        List<String> errors = List.of("Please use a different id to search.");
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), errors);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
