package com.example.Backend.controller.SearchAndFilter;

import com.example.Backend.model.Shelter;
import com.example.Backend.service.searchAndFilter.AdminSearchAndFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/")
public class AdminSearchAndFilterController {

    @Autowired
    private AdminSearchAndFilterService adminSearchAndFilterService;

    @GetMapping("search")
    public List<Shelter> searchForShelter(){
        return null;
    }

}
