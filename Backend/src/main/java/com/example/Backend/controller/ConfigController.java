package com.example.Backend.controller;

import com.example.Backend.service.pet.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("fetch")
public class ConfigController {
    @Autowired
    ConfigService configService;

    @GetMapping("countries")
    public List<String> getAllCountries(){
        return configService.getAllCountries();
    }
    @GetMapping("cities")
    public List<String> getCities(String country){
        return configService.getCities(country);

    }
    @GetMapping("species")
    public List<String> getAllSpecies(){
        return configService.getAllSpecies();
    }

    @GetMapping("breed")
    public List<String> getBreeds(String species){
        return configService.getBreeds(species);
    }

}
