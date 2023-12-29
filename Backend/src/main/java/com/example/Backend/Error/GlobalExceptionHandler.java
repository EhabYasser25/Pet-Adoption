//package com.example.Backend.Error;
//
//import com.example.Backend.DTO.ErrorDTO;
//import com.example.Backend.DTO.Response;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
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
//        return ResponseEntity.status(HttpStatus.OK).body(Response.builder()
//                .status(400)
//                .data(fieldErrors)
//                .build());
//    }
//
//    @ExceptionHandler(GlobalException.class)
//    public ResponseEntity<ErrorDTO> customizedException(GlobalException ex){
//        ErrorDTO error = ex.getStatusCode().getError();
//        System.out.println("heyyyyy");
//        return ResponseEntity
//                .status(ex.getHttpStatus()).body(error);    }
//
//}
//
