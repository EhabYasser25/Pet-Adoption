package com.example.Backend.controller.SearchAndFilter;

import com.example.Backend.DTO.SearchAndFilter.AdminSearchAndFilterDTO;
import com.example.Backend.model.Shelter;
import com.example.Backend.service.searchAndFilter.AdminSearchAndFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test/admin/")
public class AdminSearchAndFilterController {

    @Autowired
    private AdminSearchAndFilterService adminSearchAndFilterService;

    @GetMapping("search")
    public List<Shelter> searchForShelter(@RequestBody AdminSearchAndFilterDTO adminSearchAndFilterDTO){
        System.out.println(adminSearchAndFilterDTO.getCity());
        return adminSearchAndFilterService.searchForShelter(adminSearchAndFilterDTO);
    }
    @GetMapping("shelter-profile")
    public Shelter viewShelterProfile(@RequestParam int shelterID){
        return adminSearchAndFilterService.viewShelterProfile(shelterID);
    }

}
