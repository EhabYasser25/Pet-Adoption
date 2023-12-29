package com.example.Backend.service.pet;

import com.example.Backend.DAO.Pet.PetTypeDAO;
import com.example.Backend.model.pet.species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetTypeService {
    @Autowired
    PetTypeDAO petTypeDAO;

//    public species getById(int id) {
//        return petTypeDAO.getById(id);
//    }
}
