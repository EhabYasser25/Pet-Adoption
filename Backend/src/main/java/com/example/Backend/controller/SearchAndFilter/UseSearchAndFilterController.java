package com.example.Backend.controller.SearchAndFilter;

import com.example.Backend.DTO.SearchAndFilter.UserSearchAndFilterDTO;
import com.example.Backend.model.pet.Pet;
import com.example.Backend.model.pet.PetSummary;
import com.example.Backend.service.searchAndFilter.UserSearchAndFilterService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UseSearchAndFilterController {
    @Autowired
    private UserSearchAndFilterService userSearchAndFilterService;

    @GetMapping("/search")
    List<PetSummary> searchAndFilter(UserSearchAndFilterDTO userSearchAndFilterDTO){
        return userSearchAndFilterService.searchAndFilter(userSearchAndFilterDTO);

    }
    @GetMapping("pet-profile")
    Pet viewPet(int perId){
        userSearchAndFilterService.viewPet(perId);
        return null;
    }
}
