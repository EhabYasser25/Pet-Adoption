package com.example.Backend.DAO;

import com.example.Backend.model.Shelter; // Assume you have a Shelter model class
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShelterDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Shelter> getAllShelters() {
        return jdbcTemplate.query("SELECT * FROM shelters", new BeanPropertyRowMapper<>(Shelter.class));
    }

    public Shelter updateShelterById(int id, String name, String address) {
        try {
            String updateSql = "UPDATE shelters SET name = ?, address = ? WHERE id = ?";
            int result = jdbcTemplate.update(updateSql, name, address, id);

            if (result > 0) {
                String selectSql = "SELECT * FROM shelters WHERE id = ?";
                return jdbcTemplate.queryForObject(
                        selectSql,
                        new BeanPropertyRowMapper<>(Shelter.class),
                        id
                );
            } else {
                System.out.println("No shelter was found with id: " + id);
                return null;
            }
        } catch (DataAccessException e) {
            System.out.println("Error updating shelter with id: " + id);
            return null;
        }
    }

    public boolean deleteShelterById(int id) {
        try {
            String sql = "DELETE FROM shelters WHERE id = ?";
            int result = jdbcTemplate.update(sql, id);
            return result > 0;
        } catch (DataAccessException e) {
            System.out.println("Error deleting shelter with id: " + id);
            return false;
        }
    }

}
