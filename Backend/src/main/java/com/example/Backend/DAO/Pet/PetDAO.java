package com.example.Backend.DAO.Pet;


import com.example.Backend.model.pet.Pet;
import com.example.Backend.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.*;

@Repository
public class PetDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    SpeciesDAO speciesDAO;

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
//    public ArrayList<Byte> getImageById(int id) {
//        try {
//            BeanPropertyRowMapper<ArrayList<Byte>> rowMapper = new BeanPropertyRowMapper<>(ArrayList<Byte>.class);
//            return this.jdbcTemplate.queryForObject(
//                    "SELECT image FROM pet WHERE id = ?", rowMapper, id);
//
//        } catch (EmptyResultDataAccessException e) {
//            // Handle case where user is not found
//            System.out.println("Pet not found with id: " + id);
//            return null;
//        }
//    }

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
                    "SELECT breed FROM pet WHERE species= ?",  new SingleColumnRowMapper<>(String.class),species);
            System.out.println(result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println("no breeds");
            return null;
        }
    }
    

    public boolean insertPet(Pet pet) {
        try {
            if (!speciesDAO.speciesExists(pet.getSpecies())) {
                speciesDAO.insertSpecies(pet.getSpecies());
            }

            String query = "INSERT INTO pet (species, name, birthdate, gender, is_sterilized, is_vaccinated, is_house_trained, image, breed, shelter_id, shelter_location_city, shelter_location_country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            int result = jdbcTemplate.update(query, pet.getSpecies(), pet.getName(), pet.getBirthDate(), pet.getGender(), pet.isSterilized(), pet.isVaccinated(), pet.isHouseTrained(), pet.getImage(), pet.getBreed(), pet.getShelterId(), pet.getShelterLocationCity(), pet.getShelterLocationCountry());
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editPet(Pet pet) {
        try {
            String query = "UPDATE pet SET name=?, is_sterilized=?, is_vaccinated=?, is_house_trained=? WHERE id=?";
            int result = jdbcTemplate.update(query, pet.getName(), pet.isSterilized(), pet.isVaccinated(), pet.isHouseTrained(), pet.getId());
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePet(int id) {
        try {
            String query = "DELETE FROM pet WHERE id = ?";
            int result = jdbcTemplate.update(query, id);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}