package com.example.Backend.controller.user;

import com.example.Backend.DTO.*;
import com.example.Backend.Error.*;
import com.example.Backend.enums.*;
import com.example.Backend.model.application.*;
import com.example.Backend.service.user.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

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

        if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorCode.LOGIN_FAILED.getError());
        }

        System.out.println(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorDTO.builder().code(101).message(ex.getMessage()).build());

    }

    @PostMapping("submit/application")
    public ResponseEntity<String> submitApplication(@AuthenticationPrincipal String username, @RequestBody Application application){

        userService.submitApplication(application, username);
        return ResponseEntity.ok("Application submitted successfully");
    }

}
