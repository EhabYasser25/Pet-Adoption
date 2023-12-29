package com.example.Backend.ControllerAdvice;


import com.example.Backend.DTO.ErrorDTO;
import com.example.Backend.Error.GlobalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorDTO> customizedException(GlobalException ex){
        ErrorDTO error = ex.getErrorCode().getError();
        System.out.println("heyyyyy");
        return ResponseEntity
                .status(ex.getHttpStatus()).body(error);    }
}
