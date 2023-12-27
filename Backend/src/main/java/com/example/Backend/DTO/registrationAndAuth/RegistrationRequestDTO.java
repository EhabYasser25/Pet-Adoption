package com.example.Backend.DTO.registrationAndAuth;

import com.example.Backend.enums.Genders;
import com.example.Backend.validator.Birthdate;
import com.example.Backend.validator.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RegistrationRequestDTO {

    @Email
    private String email;

    @StrongPassword
    private String password;

    @NotNull (message = "please provide a username ")
    private String username;

    @NotNull (message = "please provide a firstname")
    private String firstname;

    private String lastName;

    @Birthdate
    private LocalDate birthdate;

    private Genders gender;



}
