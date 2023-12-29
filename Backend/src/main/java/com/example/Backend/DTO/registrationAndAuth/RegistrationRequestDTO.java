package com.example.Backend.DTO.registrationAndAuth;

import com.example.Backend.emum.Gender;
import com.example.Backend.validator.Birthdate;
import com.example.Backend.validator.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank (message = "please provide a username ")
    private String username;

    @NotBlank(message = "please provide a firstname")
    private String firstName;

    @NotBlank(message = "please provide your last name")
    private String lastName;

    private String middleName;

    @Birthdate
    private LocalDate birthdate;

    private Gender gender;

    @NotBlank (message =  "please provide your phone number")
    private String phoneNo;

}
