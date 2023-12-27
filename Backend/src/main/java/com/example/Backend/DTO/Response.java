package com.example.Backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Response<T> {
    private int status;
    private String message;
    private T data;
}