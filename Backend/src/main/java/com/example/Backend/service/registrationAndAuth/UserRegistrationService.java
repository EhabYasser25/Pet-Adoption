package com.example.Backend.service.registrationAndAuth;


import com.example.Backend.DAO.user.UserDAO;
import com.example.Backend.DTO.registrationAndAuth.RegistrationRequestDTO;
import com.example.Backend.Error.GlobalException;
import com.example.Backend.emum.Role;
import com.example.Backend.emum.StatusCode;
import com.example.Backend.model.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserDAO userDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String registerUser(RegistrationRequestDTO registrationRequest) throws JsonProcessingException, GlobalException {

        check_credentials(registrationRequest);
        User user = get_user(registrationRequest);
        user.setRole(Role.USER);
        if (!userDAO.insertUser(user)){
            throw new GlobalException(StatusCode.REGISTRATION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return authenticationService.authenticate(registrationRequest.getUsername(),registrationRequest.getPassword());

    }

    public void check_credentials(RegistrationRequestDTO registrationRequest) throws GlobalException {

        // check if email already exists
        if (userDAO.existsByEmail(registrationRequest.getEmail())){
            throw new GlobalException(StatusCode.EMAIL_ALREADY_EXIST, HttpStatus.CONFLICT);
        }

        // check if username already exists
        if (userDAO.existsByUsername(registrationRequest.getUsername())){
            throw new GlobalException(StatusCode.USERNAME_ALREADY_EXIST, HttpStatus.CONFLICT);
        }
    }

    public User get_user(RegistrationRequestDTO registrationRequest) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        User user = objectMapper.readValue(objectMapper.writeValueAsString(registrationRequest), User.class);
        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());
        user.setPassword(encodedPassword);

        return user;

    }



}
