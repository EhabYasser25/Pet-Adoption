package com.example.Backend.DAO.Pet;

import com.example.Backend.mapper.pet.PetMapper;
import com.example.Backend.mapper.pet.PetTypeMapper;
import com.example.Backend.model.pet.Pet;
import com.example.Backend.model.pet.PetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PetDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PetMapper petMapper;

    public Pet getById(int id) {
        try {
            Pet result = this.jdbcTemplate.queryForObject(
                    "SELECT * FROM pet WHERE id = ?", petMapper, id);
            System.out.println(result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println("Pet not found with id: " + id);
            return null;
        }
    }
}
