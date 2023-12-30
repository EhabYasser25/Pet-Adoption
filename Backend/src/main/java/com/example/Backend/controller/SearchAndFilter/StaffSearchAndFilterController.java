package com.example.Backend.controller.SearchAndFilter;

import com.example.Backend.DTO.SearchAndFilter.StaffSearchAndFilterDTO;
import com.example.Backend.model.pet.Pet;
import com.example.Backend.model.pet.PetSummary;
import com.example.Backend.service.searchAndFilter.StaffSearchAndFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffSearchAndFilterController {

    @Autowired
    private StaffSearchAndFilterService staffSearchAndFilterService;


    @GetMapping("/search")
    List<PetSummary> searchAndFilter(StaffSearchAndFilterDTO staffSearchAndFilterDTO){
        return staffSearchAndFilterService.searchAndFilter(staffSearchAndFilterDTO);

    }
    @GetMapping("pet-profile")
    Pet viewPet(int perId){
        return staffSearchAndFilterService.viewPet(perId);
    }
}
