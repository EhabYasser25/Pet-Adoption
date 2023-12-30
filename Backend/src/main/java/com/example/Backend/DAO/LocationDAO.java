package com.example.Backend.DAO;

import com.example.Backend.model.pet.PetSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<String> getCountries() {
        try {
            BeanPropertyRowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);
            List<String> result = this.jdbcTemplate.query(
                    "SELECT country FROM location", rowMapper);
            System.out.println(result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println("no countries");
            return null;
        }
    }
    public List<String> getCities(String country) {
        try {
            BeanPropertyRowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);
            List<String> result = this.jdbcTemplate.query(
                    "SELECT city FROM location WHERE country= ? ", rowMapper,country);
            System.out.println(result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println("no cities");
            return null;
        }
    }
}
