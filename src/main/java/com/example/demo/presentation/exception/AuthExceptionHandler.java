package com.example.demo.presentation.exception;

import com.example.demo.core.exception.AuthenticationException;
import com.example.demo.core.exception.EmailAlreadyExistsException;
import com.example.demo.core.exception.IncorrectPasswordException;
import com.example.demo.presentation.controller.AuthController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice(assignableTypes = AuthController.class)
public class AuthExceptionHandler {
    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ApiError> incorrectPasswordException(IncorrectPasswordException ex) {
        List<String> errors = List.of("Please ensure that your password is correct.");
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), errors);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        List<String> errors = List.of("Email is already registered.", "Please use a different email address.");
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage(), errors);
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex) {
        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }
}
