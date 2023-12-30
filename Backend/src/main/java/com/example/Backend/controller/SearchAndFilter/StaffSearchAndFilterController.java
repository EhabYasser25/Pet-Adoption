package com.example.Backend.controller.SearchAndFilter;

import com.example.Backend.DAO.Pet.PetDAO;
import com.example.Backend.DTO.SearchAndFilter.StaffSearchAndFilterDTO;
import com.example.Backend.model.pet.Pet;
import com.example.Backend.model.pet.PetSummary;
import com.example.Backend.service.searchAndFilter.StaffSearchAndFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffSearchAndFilterController {

    @Autowired
    private StaffSearchAndFilterService staffSearchAndFilterService;
    @Autowired
    private PetDAO petDAO;


    @PostMapping("/search")
    List<PetSummary> searchAndFilter(@AuthenticationPrincipal String userName, @RequestBody StaffSearchAndFilterDTO staffSearchAndFilterDTO){
        System.out.println(staffSearchAndFilterDTO.getName());
        return staffSearchAndFilterService.searchAndFilter(staffSearchAndFilterDTO,userName);

    }
    @GetMapping("/pet-profile")
    Pet viewPet(@RequestParam int perId){
        return staffSearchAndFilterService.viewPet(perId);
    }

}
