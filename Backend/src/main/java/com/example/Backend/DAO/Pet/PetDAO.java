package com.example.Backend.DAO.Pet;


import com.example.Backend.model.pet.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PetDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Pet getById(int id) {
        try {
            BeanPropertyRowMapper<Pet> rowMapper = new BeanPropertyRowMapper<>(Pet.class);
            Pet result = this.jdbcTemplate.queryForObject(
                    "SELECT * FROM pet WHERE id = ?", rowMapper, id);
            System.out.println(result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println("Pet not found with id: " + id);
            return null;
        }
    }
    public List<Pet> getPetByQuery(String query) {
        try {
            BeanPropertyRowMapper<Pet> rowMapper = new BeanPropertyRowMapper<>(Pet.class);
            List<Pet> result = this.jdbcTemplate.query(
                    query, rowMapper);
            System.out.println(result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println(query);
            return null;
        }
    }
    public List<String> getBreedBySpecies(String species) {
        try {
            BeanPropertyRowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);
            List<String> result = this.jdbcTemplate.query(
                    "SELECT breed FROM pet WHERE species= ?", rowMapper,species);
            System.out.println(result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println("no breeds");
            return null;
        }
    }
    
}