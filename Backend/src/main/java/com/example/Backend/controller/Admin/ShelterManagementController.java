package com.example.Backend.controller.Admin;


import com.example.Backend.DTO.ErrorDTO;
import com.example.Backend.DTO.registrationAndAuth.StaffMemberDTO;
import com.example.Backend.Error.GlobalException;
import com.example.Backend.service.Admin.ShelterManagementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class ShelterManagementController {

    @Autowired
    ShelterManagementService shelterManagementService;

    @PostMapping("/add-staff-member")
    ResponseEntity<Void> addStaffMember(@RequestBody StaffMemberDTO staffMember) throws JsonProcessingException, GlobalException {
        shelterManagementService.registerStaffMember(staffMember);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorDTO> customizedException(GlobalException ex){
        ErrorDTO error = ex.getStatusCode().getError();
        System.out.println("heyyyyy");
        return ResponseEntity
                .status(ex.getHttpStatus()).body(error);    }
}
