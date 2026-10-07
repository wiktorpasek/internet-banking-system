package com.bank.bankingsystem.exception;

import com.bank.bankingsystem.dto.ErrorResponse; // POPRAWIONY IMPORT!
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException ex) {

        ErrorResponse error = new ErrorResponse(ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            errors.put(field, message);
        });
        return  ResponseEntity.badRequest().body(errors);
    }
}