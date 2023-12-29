package com.example.Backend.controller.Admin;


import com.example.Backend.DTO.ErrorDTO;
import com.example.Backend.DTO.registrationAndAuth.StaffMemberDTO;
import com.example.Backend.Error.GlobalException;
import com.example.Backend.enums.ErrorCode;
import com.example.Backend.model.Shelter;
import com.example.Backend.model.user.Staff;
import com.example.Backend.service.Admin.ShelterManagementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        System.out.println(ex.getMessage());
        System.out.println(ex.getCause().toString());
        System.out.println(ex.getStackTrace().toString().toString());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorCode.UNHANDLED_EXCEPTION.getError());    }

    @GetMapping("/shelters")
    public ResponseEntity<List<Shelter>> getAllShelters () {
        List<Shelter> shelters = shelterManagementService.getAllShelters();
        return ResponseEntity.ok(shelters);
    }

//    @PostMapping("/add-shelter")
//    public ResponseEntity<Shelter> addShelter (@RequestBody Staff staff) {
//
//    }

    @PutMapping("/modify-shelter/{id}/{name}/{address}")
    public ResponseEntity<Shelter> updateShelter (
            @PathVariable (value = "id") int id,
            @PathVariable (value = "name") String name,
            @PathVariable (value = "address") String address
    ) {
        Shelter shelter = shelterManagementService.updateShelter(id, name, address);
        return ResponseEntity.ok(shelter);
    }

    @DeleteMapping("/delete-shelter/{id}")
    public ResponseEntity<?> deleteShelter (@PathVariable (value = "id") int id) {
        return ResponseEntity.ok(shelterManagementService.deleteShelter(id));
    }

    @GetMapping("/staff/{shelter_id}")
    public ResponseEntity<List<Staff>> getAllShelterStaff (@PathVariable (value = "shelter_id") int shelterId) {
        List<Staff> staffList = shelterManagementService.getAllShelterStaff(shelterId);
        return ResponseEntity.ok(staffList);
    }

//    @PutMapping("/update-staff")
//    public ResponseEntity<Staff> updateStaffMember (@RequestBody Staff staff) {
//
//    }

    @DeleteMapping("/delete-staff/{staff-id}")
    public ResponseEntity<?> deleteStaffMember (@PathVariable (value = "staff-id") int staffId) {
        return ResponseEntity.ok(shelterManagementService.deleteStaffMember(staffId));
    }
}