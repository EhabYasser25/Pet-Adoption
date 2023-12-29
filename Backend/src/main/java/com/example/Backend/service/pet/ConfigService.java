package com.example.Backend.service.pet;

import com.example.Backend.DAO.LocationDAO;
import com.example.Backend.DAO.Pet.PetDAO;
import com.example.Backend.DAO.Pet.SpeciesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigService {
    @Autowired
    private SpeciesDAO speciesDAO;
    @Autowired
    private LocationDAO locationDAO;
    @Autowired
    private PetDAO petDAO;

    public List<String> getAllCountries() {
        return locationDAO.getCountries();
    }

    public List<String> getCities(String country) {
        return locationDAO.getCities(country);
    }

    public List<String> getAllSpecies() {
        return speciesDAO.getAllSpecies();
    }


    public List<String> getBreeds(String species) {
       return petDAO.getBreedBySpecies(species);

    }
}
