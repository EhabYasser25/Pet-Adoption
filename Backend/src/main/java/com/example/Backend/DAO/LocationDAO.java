package com.example.Backend.DAO;

import com.example.Backend.model.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LocationDAO {
    private final JdbcTemplate jdbcTemplate;

    public List<String> getAllCountries() {
        return jdbcTemplate.queryForList("SELECT DISTINCT country FROM LOCATION", String.class);
    }

    public List<String> getCities(String country) {
        return jdbcTemplate.queryForList("SELECT city FROM LOCATION WHERE COUNTRY = ?", String.class, country);
    }

}
