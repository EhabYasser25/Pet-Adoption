package com.example.Backend.controller;

import com.example.Backend.model.Location;
import com.example.Backend.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/location")
public class LocationController {
    private final LocationService locationService;

    @GetMapping("/all-countries")
    public ResponseEntity<List<String>> getAllCountries () {
        return ResponseEntity.ok(locationService.getAllCountries());
    }

    @GetMapping("/{country}")
    public ResponseEntity<List<String>> getCitiesByCountry(@PathVariable (value = "country") String country) {
        return ResponseEntity.ok(locationService.getCities(country));
    }

}
