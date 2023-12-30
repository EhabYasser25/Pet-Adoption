package com.example.Backend.DAO;

import com.example.Backend.model.Shelter; // Assume you have a Shelter model class
import com.example.Backend.model.pet.Pet;
import com.example.Backend.model.pet.PetSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShelterDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Shelter> getAllShelters() {
        return jdbcTemplate.query("SELECT * FROM shelter", new BeanPropertyRowMapper<>(Shelter.class));
    }
    public Shelter getShelterById(int shelterId) {
        try {
            BeanPropertyRowMapper<Shelter> rowMapper = new BeanPropertyRowMapper<>(Shelter.class);
            Shelter result = this.jdbcTemplate.queryForObject(
                    "SELECT * FROM pet WHERE id = ?", rowMapper, shelterId);
            System.out.println(result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println("Pet not found with id: " + shelterId);
            return null;
        }
    }


    public boolean updateShelterById(Shelter shelter) {
        try {
            String updateSql = "UPDATE shelter SET name = ?, address = ?, location_country = ?, location_city = ? WHERE id = ?";
            int result = jdbcTemplate.update(
                    updateSql,
                    shelter.getName(),
                    shelter.getAddress(),
                    shelter.getLocationCity(),
                    shelter.getLocationCountry(),
                    shelter.getId());
            return  result > 0;
        } catch (DataAccessException e) {
            System.out.println("Error updating shelter with id: " + shelter.getId());
            return false;
        }
    }

    public boolean deleteShelterById(int id) {
        try {
            String sql = "DELETE FROM shelter WHERE id = ?";
            int result = jdbcTemplate.update(sql, id);
            return result > 0;
        } catch (DataAccessException e) {
            System.out.println("Error deleting shelter with id: " + id);
            return false;
        }
    }

    @Transactional
    public boolean addShelter(Shelter shelter) {
        try {
            String shelterQuery = "INSERT INTO shelter (name, address, location_country, location_city) " +
                    "VALUES (?, ?, ?, ?)";

            jdbcTemplate.update(
                    shelterQuery,
                    shelter.getName(),
                    shelter.getAddress(),
                    shelter.getLocationCountry(),
                    shelter.getLocationCity()
            );

            return true; // Return true if the insertion was successful
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Shelter> getShelterByQuery(String query){
            try {
                BeanPropertyRowMapper<Shelter> rowMapper = new BeanPropertyRowMapper<>(Shelter.class);
                List<Shelter> result = this.jdbcTemplate.query(
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
