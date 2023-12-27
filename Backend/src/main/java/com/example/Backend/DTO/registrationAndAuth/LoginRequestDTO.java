package com.example.Backend.DTO.registrationAndAuth;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class LoginRequestDTO {

    @NotNull (message = "please enter your username or email")
    private String usernameOrEmail;

    @NotNull (message = "please enter your password")
    private String password;

}
