package com.example.Backend.controller.Admin;

import com.example.Backend.model.Shelter;
import com.example.Backend.model.user.Staff;
import com.example.Backend.service.Admin.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminDashboardManagement {
    final AdminDashboardService adminDashboardService;

    @GetMapping("/shelters")
    public ResponseEntity<List<Shelter>> getAllShelters () {
        List<Shelter> shelters = adminDashboardService.getAllShelters();
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
        Shelter shelter = adminDashboardService.updateShelter(id, name, address);
        return ResponseEntity.ok(shelter);
    }

    @DeleteMapping("/delete-shelter/{id}")
    public ResponseEntity<?> deleteShelter (@PathVariable (value = "id") int id) {
        return ResponseEntity.ok(adminDashboardService.deleteShelter(id));
    }

    @GetMapping("/staff/{shelter_id}")
    public ResponseEntity<List<Staff>> getAllShelterStaff (@PathVariable (value = "shelter_id") int shelterId) {
        List<Staff> staffList = adminDashboardService.getAllShelterStaff(shelterId);
        return ResponseEntity.ok(staffList);
    }

//    @PutMapping("/update-staff")
//    public ResponseEntity<Staff> updateStaffMember (@RequestBody Staff staff) {
//
//    }

    @DeleteMapping("/delete-staff/{staff-id}")
    public ResponseEntity<?> deleteStaffMember (@PathVariable (value = "staff-id") int staffId) {
        return ResponseEntity.ok(adminDashboardService.deleteStaffMember(staffId));
    }
}
