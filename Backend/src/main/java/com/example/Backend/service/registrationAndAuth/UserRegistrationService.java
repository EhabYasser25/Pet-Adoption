package com.example.Backend.service.registrationAndAuth;


import com.example.Backend.DTO.registrationAndAuth.RegistrationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    @Autowired
    AuthenticationService authenticationService;

    public void registerUser(RegistrationRequestDTO registrationRequest) {

        // check if email already exists

        // check if username already exists

        // try to save user in database

        // catch (in case of concurrency)


    }


}
