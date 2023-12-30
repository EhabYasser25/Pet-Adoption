package com.example.Backend.DAO.user;


import com.example.Backend.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getById(int id) {
        try {
            BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
            User user = this.jdbcTemplate.queryForObject(
                    "SELECT * FROM user WHERE id = ?", rowMapper, id);
            return user;
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println("User not found with ID: " + id);
            return null;
        }
    }
    public Integer getUserIdByUserName(String userName){
        try {
            return this.jdbcTemplate.queryForObject(
                    "SELECT id FROM user WHERE username = ?", Integer.class, userName);
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println("User not found with username: " + userName);
            return null;
        }
    }


    public User getByUsernameOrEmail(String usernameOrEmail) {
        try {
            String query = "SELECT * FROM user WHERE username = ? OR email = ?";
            BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
            User user = this.jdbcTemplate.queryForObject(query, rowMapper, usernameOrEmail, usernameOrEmail);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public boolean existsByEmail(String email) {
        try {
            String query = "SELECT COUNT(*) FROM user WHERE email = ?";
            Integer count = jdbcTemplate.queryForObject(query, Integer.class, email);
            return count != null && count > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
    public boolean existsByUsername(String username) {
        try {
            String query = "SELECT COUNT(*) FROM user WHERE username = ?";
            Integer count = jdbcTemplate.queryForObject(query, Integer.class, username);
            return count != null && count > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }


    public boolean insertUser(User user) {
        try {
            String query = "INSERT INTO user (first_name, middle_name, last_name, username, password, email, phone_no, gender, birthdate, role) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            int rowsAffected = jdbcTemplate.update(query,
                    user.getFirstName(),
                    user.getMiddleName(),
                    user.getLastName(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getPhoneNo(),
                    user.getGender().toString(),  // Assuming Gender is an Enum
                    user.getBirthdate(),
                    user.getRole().toString());   // Assuming Role is an Enum

            return rowsAffected > 0;
        } catch (DataAccessException e) {
            // Handle database-related exceptions
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user) {
        try {
            String sql = "UPDATE user SET first_name = ?, middle_name = ?, last_name = ?, username = ?, email = ?, phone_no = ?, birthdate = ? WHERE id = ?";

            int rowsAffected = jdbcTemplate.update(sql,
                    user.getFirstName(),
                    user.getMiddleName(),
                    user.getLastName(),
                    user.getUsername(),
                    user.getEmail(), // TODO check regex
                    user.getPhoneNo(),
                    user.getBirthdate(),
                    user.getId());

            return rowsAffected > 0;
        } catch (DataAccessException e) {
            System.out.println("Error updating user with ID: " + user.getId());
            e.printStackTrace();
            return false;
        }
    }
}