package com.example.Backend.controller.Admin;


import com.example.Backend.DTO.ErrorDTO;
import com.example.Backend.DTO.registrationAndAuth.StaffMemberDTO;
import com.example.Backend.Error.GlobalException;
import com.example.Backend.enums.ErrorCode;

import com.example.Backend.model.Shelter;
import com.example.Backend.model.user.Staff;
import com.example.Backend.model.user.User;
import com.example.Backend.service.Admin.ShelterManagementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ShelterManagementController {

    @Autowired
    ShelterManagementService shelterManagementService;

    @PostMapping("/add-staff-member")
    ResponseEntity<Void> addStaffMember(@RequestBody StaffMemberDTO staffMember) throws JsonProcessingException, GlobalException {
        shelterManagementService.registerStaffMember(staffMember);
        return ResponseEntity.ok().build();
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

    @GetMapping("/shelters")
    public ResponseEntity<List<Shelter>> getAllShelters() {
        List<Shelter> shelters = shelterManagementService.getAllShelters();
        return ResponseEntity.ok(shelters);
    }

    @PostMapping("/save-shelter")
    public ResponseEntity<?> saveShelter(@RequestBody Shelter shelter) {
        return ResponseEntity.ok(shelterManagementService.saveShelter(shelter));
    }

    @PostMapping("/modify-shelter")
    public ResponseEntity<?> updateShelter(@RequestBody Shelter shelter) {
        return ResponseEntity.ok(shelterManagementService.updateShelter(shelter));
    }

    @DeleteMapping("/delete-shelter/{id}")
    public ResponseEntity<?> deleteShelter(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok(shelterManagementService.deleteShelter(id));
    }

    @GetMapping("/shelters/{shelter_id}/staff")
    public ResponseEntity<List<User>> getAllShelterStaff(@PathVariable(value = "shelter_id") int shelterId) {
        List<User> staffList = shelterManagementService.getAllShelterStaff(shelterId);
        return ResponseEntity.ok(staffList);
    }

    @DeleteMapping("/staff/delete/{staff-id}")
    public ResponseEntity<?> deleteStaffMember(@PathVariable(value = "staff-id") int staffId) {
        return ResponseEntity.ok(shelterManagementService.deleteStaffMember(staffId));
    }

    @PostMapping("/modify-staff")
    public ResponseEntity<?> updateStaffMember(@RequestBody StaffMemberDTO staffMember) {
        return ResponseEntity.ok(shelterManagementService.updateStaffMember(staffMember));
    }
}
