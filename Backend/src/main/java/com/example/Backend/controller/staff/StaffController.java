package com.example.Backend.controller.staff;

import com.example.Backend.DAO.user.*;
import com.example.Backend.DTO.*;
import com.example.Backend.Error.*;
import com.example.Backend.enums.*;
import com.example.Backend.model.application.*;
import com.example.Backend.model.pet.*;
import com.example.Backend.model.user.*;
import com.example.Backend.service.staff.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

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

    @PostMapping("/add/pet")
    public ResponseEntity<String> addPet(@RequestBody Pet pet) {
        System.out.println();
        staffService.addPet(pet, 50);
        return ResponseEntity.ok("Pet added successfully");
    }

    @PostMapping("/edit/pet")
    public ResponseEntity<String> editPet(@RequestBody Pet pet) {
        System.out.println();
        staffService.editPet(pet);
        return ResponseEntity.ok("Pet updated successfully");
    }

    @GetMapping("view/applications")
    public ResponseEntity<List<Application>> viewApplications() {
        List<Application> applications = staffService.getAllApplications();
        return ResponseEntity.ok(applications);
    }

    @DeleteMapping("/delete/pet/{id}")
    public ResponseEntity<String> deletePet(@PathVariable int id) {
        staffService.deletePet(id);
        return ResponseEntity.ok("Pet deleted successfully");
    }

}
