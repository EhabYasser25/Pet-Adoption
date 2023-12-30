package com.example.Backend.controller.SearchAndFilter;

import com.example.Backend.DTO.ErrorDTO;
import com.example.Backend.DTO.SearchAndFilter.UserSearchAndFilterDTO;
import com.example.Backend.Error.GlobalException;
import com.example.Backend.enums.ErrorCode;
import com.example.Backend.model.pet.Pet;
import com.example.Backend.model.pet.PetSummary;
import com.example.Backend.service.searchAndFilter.UserSearchAndFilterService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UseSearchAndFilterController {
    @Autowired
    private UserSearchAndFilterService userSearchAndFilterService;


    @PostMapping("/search")
    List<PetSummary> searchAndFilter(@RequestBody UserSearchAndFilterDTO userSearchAndFilterDTO){
        return userSearchAndFilterService.searchAndFilter(userSearchAndFilterDTO);

    }
    @GetMapping("/pet-profile")
    Pet viewPet(@RequestParam("petId") int perId){
        Pet result =userSearchAndFilterService.viewPet(perId);
        System.out.println(result.getImage().length);
       return result;
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

        if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorCode.LOGIN_FAILED.getError());
        }

        System.out.println(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorDTO.builder().code(101).message(ex.getMessage()).build());

    }
}
