package com.example.Backend.controller.RegistrationAndAuth;


import com.example.Backend.DTO.Response;
import com.example.Backend.DTO.registrationAndAuth.LoginRequestDTO;
import com.example.Backend.enums.StatusCode;
import com.example.Backend.service.registrationAndAuth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {


    @Autowired
    AuthenticationService loginService;
    @PostMapping("/login")
    public ResponseEntity<Response<Object>> login(@Valid @RequestBody LoginRequestDTO request)  {

        String jwt = loginService.authenticate(request.getUsernameOrEmail(), request.getPassword());

        return ResponseEntity
                .ok(StatusCode.SUCCESSFUL_REGISTRATION_OR_LOGIN.getResponse(jwt));
    }
}
