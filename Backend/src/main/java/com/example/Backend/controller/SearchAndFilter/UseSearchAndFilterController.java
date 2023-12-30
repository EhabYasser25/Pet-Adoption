package com.example.Backend.controller.SearchAndFilter;

import com.example.Backend.DTO.SearchAndFilter.UserSearchAndFilterDTO;
import com.example.Backend.model.pet.Pet;
import com.example.Backend.model.pet.PetSummary;
import com.example.Backend.service.searchAndFilter.UserSearchAndFilterService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test/user")
public class UseSearchAndFilterController {
    @Autowired
    private UserSearchAndFilterService userSearchAndFilterService;


    @GetMapping("/search")
    List<PetSummary> searchAndFilter(@RequestBody UserSearchAndFilterDTO userSearchAndFilterDTO){

        return userSearchAndFilterService.searchAndFilter(userSearchAndFilterDTO);

    }
    @GetMapping("/pet-profile")
    Pet viewPet(@RequestParam("petId") int perId){
        Pet result =userSearchAndFilterService.viewPet(perId);
        System.out.println(result.getImage().length);
       return result;
    }
}
