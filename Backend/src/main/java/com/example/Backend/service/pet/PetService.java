package com.example.Backend.service.pet;

import com.example.Backend.DAO.Pet.PetDAO;
import com.example.Backend.model.pet.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    @Autowired
    PetDAO petDAO;

    public Pet getById(int id) {
        return petDAO.getById(id);
    }
}
