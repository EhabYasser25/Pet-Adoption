package com.example.Backend.DAO.Pet;

import com.example.Backend.mapper.pet.PetTypeMapper;
import com.example.Backend.model.pet.PetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PetTypeDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PetTypeMapper petTypeMapper;

    public PetType getById(int id) {
        try {
            PetType result = this.jdbcTemplate.queryForObject(
                    "SELECT * FROM pet_type WHERE id = ?", petTypeMapper, id);
            System.out.println(result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println("Pet type not found with id: " + id);
            return null;
        }
    }
}