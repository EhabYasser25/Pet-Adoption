package com.example.Backend.controller;

import com.example.Backend.service.pet.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fetch")
public class ConfigController {
    @Autowired
    ConfigService configService;

    @GetMapping("/countries")
    public Object getAllCountries(){
        return configService.getAllCountries();
    }
    @GetMapping("/cities")
    public List<String> getCities(@RequestParam("country") String country){
        System.out.println(country);
        return configService.getCities(country);

    }
    @GetMapping("/species")
    public List<String> getAllSpecies(){
        return configService.getAllSpecies();
    }


    @GetMapping("/breed")
    public List<String> getBreeds(@RequestParam("species") String species){
        return configService.getBreeds(species);
    }

}
