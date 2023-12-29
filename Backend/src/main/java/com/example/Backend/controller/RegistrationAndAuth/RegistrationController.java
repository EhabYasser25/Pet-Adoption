package com.example.Backend.controller.RegistrationAndAuth;


import com.example.Backend.DTO.registrationAndAuth.RegistrationRequestDTO;
import com.example.Backend.Error.GlobalException;
import com.example.Backend.service.registrationAndAuth.UserRegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    @Autowired
    UserRegistrationService userRegistrationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationRequestDTO request) throws JsonProcessingException, GlobalException {

        String JWT = userRegistrationService.registerUser(request);

        return ResponseEntity
                .ok(JWT);
    }


}
