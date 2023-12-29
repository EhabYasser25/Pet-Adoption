package com.example.Backend.Error;


import com.example.Backend.enums.StatusCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GlobalException extends Exception{

    private StatusCode statusCode;
    private HttpStatus httpStatus;

    public GlobalException(StatusCode statusCode, HttpStatus httpStatus){
        super("global exception");
        this.statusCode = statusCode;
        this.httpStatus = httpStatus;
    }
}
