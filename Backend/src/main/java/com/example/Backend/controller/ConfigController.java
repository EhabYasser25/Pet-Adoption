package com.example.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("fetch")
public class ConfigController {
    @Autowired
    ConfigController configController;

    @GetMapping("countries")
    public List<String> getAllCountries(){
        return configController.getAllCountries();
    }
    @GetMapping("cities")
    public List<String> getCities(String country){
        return configController.getCities(country);

    }
    @GetMapping("species")
    public List<String> getAllSpecies(){
        return configController.getAllSpecies();
    }

    @GetMapping("breed")
    public List<String> getBreeds(String species){
        return configController.getBreeds(species);
    }

}
