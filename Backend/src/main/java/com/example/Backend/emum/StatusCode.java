package com.example.Backend.emum;

import com.example.Backend.DTO.ErrorDTO;
import lombok.Getter;

@Getter
public enum StatusCode {

    EMAIL_ALREADY_EXIST(10, "Email already exists"), // conflict 409
    USERNAME_ALREADY_EXIST(11, "Username already exists"), // conflict 409
    REGISTRATION_FAILED (12, "Unable to register user"); // 500 internal service error
//    SUCCESSFUL_REGISTRATION_OR_LOGIN(13, "Registration successful");

    private final int code;
    private final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorDTO getError(){
        return new ErrorDTO (code,message);
    }
}
