package com.example.Backend.DAO.Pet;


import com.example.Backend.model.pet.PetSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpeciesDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<String> getAllSpecies() {
        try {
            BeanPropertyRowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);
            List<String> result = this.jdbcTemplate.query(
                    "SELECT species FROM species", new SingleColumnRowMapper<>(String.class));
            System.out.println(result.toString());
            return result;
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println("no species");
            return null;
        }

    }

    public boolean speciesExists(String species) {
        try {
            BeanPropertyRowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);
            List<String> result = this.jdbcTemplate.query(
                    "SELECT * FROM species WHERE species = ?", rowMapper, species);
            System.out.println(result);
            return result.size() > 0;
        } catch (EmptyResultDataAccessException e) {
            System.out.println("no species");
            return false;
        }
    }

    public boolean insertSpecies(String species) {
        try {
            String query = "INSERT INTO species (species) VALUES (?)";
            int result = jdbcTemplate.update(query, species);
            return result > 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}