package com.example.Backend.DAO.application;

import com.example.Backend.model.application.*;
import lombok.*;
import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import java.util.*;

@RequiredArgsConstructor
@Repository
public class ApplicationDAO {

    private final JdbcTemplate jdbcTemplate;

    public Application getById(int id) {
        return null;
    }

    public List<Application> getAll() {
        try {
            String query = "SELECT * FROM application";
            BeanPropertyRowMapper<Application> rowMapper = new BeanPropertyRowMapper<>(Application.class);
            List<Application> applications = this.jdbcTemplate.query(query, rowMapper);
            return applications;
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("No applications found");
        }
    }

}
