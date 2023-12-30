package com.example.Backend.controller.SearchAndFilter;

import com.example.Backend.DTO.SearchAndFilter.UserSearchAndFilterDTO;
import com.example.Backend.model.pet.Pet;
import com.example.Backend.model.pet.PetSummary;
import com.example.Backend.service.searchAndFilter.UserSearchAndFilterService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UseSearchAndFilterController {
    @Autowired
    private UserSearchAndFilterService userSearchAndFilterService;

    @PostMapping("/search")
    List<PetSummary> searchAndFilter(@RequestBody UserSearchAndFilterDTO userSearchAndFilterDTO){
        System.out.println(userSearchAndFilterDTO.toString());
        System.out.println(userSearchAndFilterDTO.getCountry());
        return userSearchAndFilterService.searchAndFilter(userSearchAndFilterDTO);

    }
    @GetMapping("pet-profile")
    Pet viewPet(int perId){
        userSearchAndFilterService.viewPet(perId);
        return null;
    }
}
