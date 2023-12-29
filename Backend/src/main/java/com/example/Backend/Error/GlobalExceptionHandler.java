package com.example.Backend.Error;

import com.example.Backend.DTO.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


// not workingggggggggggggggggggggggggggggggggggggggggggggg
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> customizedExceptionn(Exception ex){
//        if (ex.)
//        ErrorDTO error = ex.getErrorCode().getError();
        System.out.println("heyyyyy");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body("errorrrrr");    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
//
//        BindingResult result = ex.getBindingResult();
//        Map<String, String> fieldErrors = new HashMap<>();
//
//        // Iterate over the errors and extract field name and default message set on annotations of these fields
//        for (FieldError error : result.getFieldErrors()) {
//            fieldErrors.put(error.getField(), error.getDefaultMessage());
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(ErrorDTO.builder()
//                .code(400)
//                .data(fieldErrors)
//                .build());
//    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorDTO> customizedException(GlobalException ex){
        ErrorDTO error = ex.getErrorCode().getError();
        System.out.println("heyyyyy");
        return ResponseEntity
                .status(ex.getHttpStatus()).body(error);    }

}

