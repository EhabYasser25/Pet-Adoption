package com.example.Backend.controller.RegistrationAndAuth;


import com.example.Backend.DTO.ErrorDTO;
import com.example.Backend.DTO.registrationAndAuth.LoginRequestDTO;
import com.example.Backend.Error.GlobalException;
import com.example.Backend.enums.ErrorCode;
import com.example.Backend.service.registrationAndAuth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {


    @Autowired
    AuthenticationService loginService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO request)  {

        String JWT = loginService.authenticate(request.getUsernameOrEmail(), request.getPassword());

        return ResponseEntity
                .ok(JWT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> customizedExceptionn(Exception ex){

        if (ex  instanceof GlobalException){
            GlobalException exception = (GlobalException)  ex;
            ErrorDTO error = exception.getErrorCode().getError();
            return ResponseEntity
                    .status(exception.getHttpStatus()).body(error);
        }
        if (ex instanceof MethodArgumentNotValidException){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).body(ErrorCode.EMAIL_ALREADY_EXIST.getError());
        }

        System.out.println(ex.getMessage());
        System.out.println(ex.getCause().toString());
        System.out.println(ex.getStackTrace().toString().toString());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorCode.UNHANDLED_EXCEPTION.getError());    }


}
