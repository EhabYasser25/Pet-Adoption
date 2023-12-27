package com.example.Backend.controller.RegistrationAndAuth;


import com.example.Backend.DTO.Response;
import com.example.Backend.DTO.registrationAndAuth.RegistrationRequestDTO;
import com.example.Backend.enums.StatusCode;
import com.example.Backend.service.registrationAndAuth.UserRegistrationService;
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
    public ResponseEntity<Response<Object>> register(@Valid @RequestBody RegistrationRequestDTO request)  {


        // string JWT = userRegistrationService.registerUser()
        String jwt = "";

        return ResponseEntity
                .ok(StatusCode.SUCCESSFUL_REGISTRATION_OR_LOGIN.getResponse(jwt));
    }


}
