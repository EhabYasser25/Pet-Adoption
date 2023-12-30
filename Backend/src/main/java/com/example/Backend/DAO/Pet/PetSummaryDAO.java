package com.example.Backend.DAO.Pet;

import com.example.Backend.model.pet.Pet;
import com.example.Backend.model.pet.PetSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetSummaryDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<PetSummary> getPetSummaryByQuery(String query) {
        try {
            BeanPropertyRowMapper<PetSummary> rowMapper = new BeanPropertyRowMapper<>(PetSummary.class);
            List<PetSummary> result = this.jdbcTemplate.query(
                    query, rowMapper);
            System.out.println(result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println(query);
            return null;
        }
    }
}
