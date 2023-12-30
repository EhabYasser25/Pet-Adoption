package com.example.Backend.service;

import com.example.Backend.DAO.LocationDAO;
import com.example.Backend.model.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationDAO locationDAO;

    public List<String> getAllCountries() {
        return locationDAO.getCountries();
    }

    public List<String> getCities(String country) {
        return locationDAO.getCities(country);
    }
}
