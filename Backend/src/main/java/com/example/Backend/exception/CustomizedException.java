package com.example.Backend.exception;

import com.example.Backend.enums.StatusCode;
import lombok.Data;

@Data
public class CustomizedException extends RuntimeException{

    private int code;

    CustomizedException(StatusCode statusCode){
        super(statusCode.getMessage());
        this.code=statusCode.getCode();
    }
}
