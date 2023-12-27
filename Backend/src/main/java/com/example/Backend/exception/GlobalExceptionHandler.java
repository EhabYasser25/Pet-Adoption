package com.example.Backend.exception;

import com.example.Backend.DTO.Response;
import com.example.Backend.DTO.registrationAndAuth.RegistrationResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();
        Map<String, String> fieldErrors = new HashMap<>();

        // Iterate over the errors and extract field name and default message set on annotations of these fields
        for (FieldError error : result.getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(Response.builder()
                .status(400)
                .data(fieldErrors)
                .build());
    }

    @ExceptionHandler(CustomizedException.class)
    public ResponseEntity<Response> customizedException(CustomizedException ex){
        return ResponseEntity
                .status(HttpStatus.OK).body(Response.builder().status(ex.getCode()).message(ex.getMessage()).build());
    }

}

