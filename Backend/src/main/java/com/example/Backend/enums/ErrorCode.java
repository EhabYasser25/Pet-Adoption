package com.example.Backend.enums;

import com.example.Backend.DTO.ErrorDTO;
import lombok.Getter;

@Getter
public enum ErrorCode {

    EMAIL_ALREADY_EXIST(10, "Email already exists"), // conflict 409
    USERNAME_ALREADY_EXIST(11, "Username already exists"), // conflict 409
    REGISTRATION_FAILED (12, "Unable to register user"),// 500 internal service error

    //Login error codes
    LOGIN_FAILED(30 , "Incorrect username or password"),

    // For debugging reasons
    BAD_DTO(100, "There is a problem with the DTO sent"),

    UNHANDLED_EXCEPTION(101,"Unhandled exception");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorDTO getError(){
        return new ErrorDTO (code,message);
    }
}