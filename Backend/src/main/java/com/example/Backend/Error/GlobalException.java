package com.example.Backend.Error;


import com.example.Backend.enums.ErrorCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GlobalException extends Exception{

    private ErrorCode errorCode;
    private HttpStatus httpStatus;

    public GlobalException(ErrorCode errorCode, HttpStatus httpStatus){
        super("global exception");
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}
