package com.example.Backend.enums;


import com.example.Backend.DTO.Response;
import lombok.Getter;

@Getter
public enum StatusCode {

    EMAIL_ALREADY_EXIST(10, "Email already exists"),
    USERNAME_ALREADY_EXIST(10, "Username already exists"),
    SUCCESSFUL_REGISTRATION_OR_LOGIN(13, "Registration sucessful");

    private final int code;
    private final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response<Void> getResponse() {
        return new Response<>(code,message,null);
    }

    public  Response<Object> getResponse(Object data) {
        return new Response<>(code,message,data);
    }
}

