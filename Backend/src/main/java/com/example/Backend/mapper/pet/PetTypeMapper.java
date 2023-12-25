package com.example.Backend.mapper.pet;

import com.example.Backend.model.pet.PetType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PetTypeMapper implements RowMapper<PetType> {
    @Override
    public PetType mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PetType(
                rs.getInt("id"),
                rs.getString("species"),
                rs.getString("breed")
        );
    }
}
