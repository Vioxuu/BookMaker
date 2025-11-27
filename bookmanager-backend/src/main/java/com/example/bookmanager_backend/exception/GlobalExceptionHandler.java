package com.example.bookmanager_backend.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) //Walidacja JSON
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        log.warn("Błąd walidacji: {}", errors);

        return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class) //Walidacja parametrów
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        log.warn("Błąd parametru: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EntityNotFoundException.class) //Gdy książka nie istnieje
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.info("Nie znaleziono encji: {}", ex.getMessage());
        return new ResponseEntity<>("Książka nie istnieje", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class) //Niepoprawny JSON
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ResponseEntity<String> handleHttpMessageNotWritableException(HttpMessageNotWritableException ex) {
        log.warn("Niepoprawny JSON: {}", ex.getMessage());
        return new ResponseEntity<>("Niepoprawny format JSON", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class) //Ochrona i fallback
    public ResponseEntity<String> handleException(Exception ex) {
        log.error("Nieoczekiwany błąd: ", ex);
        return new ResponseEntity<>("Wystąpił nieoczekiwany błąd", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
