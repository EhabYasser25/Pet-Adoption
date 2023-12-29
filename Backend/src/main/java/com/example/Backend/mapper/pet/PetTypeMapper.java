package com.example.Backend.mapper.pet;

import com.example.Backend.model.pet.species;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PetTypeMapper implements RowMapper<species> {
    @Override
    public species mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new species(
                rs.getInt("id"),
                rs.getString("species"),
                rs.getString("breed")
        );
    }
}
