package com.example.Backend.mapper.pet;

import com.example.Backend.enums.Gender;
import com.example.Backend.model.pet.Pet;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PetMapper implements RowMapper<Pet> {
    @Override
    public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Pet (
                rs.getInt("id"),
                rs.getInt("type_id"),
                rs.getString("name"),
                rs.getDate("birthdate"),
                Gender.valueOf(rs.getString("gender")), // Assuming 'Gender' is an Enum in your Pet class
                rs.getBoolean("is_vaccinated"),
                rs.getBoolean("is_house_trained"),
                rs.getBoolean("is_sterilized"),
                rs.getBlob("image"),
                rs.getTimestamp("release_time_stamp")

        );
    }
}