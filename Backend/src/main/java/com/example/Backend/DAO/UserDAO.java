package com.example.Backend.DAO;

import com.example.Backend.mapper.user.UserRowMapper;
import com.example.Backend.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRowMapper userRowMapper;

    public User getUserById(int id) {
        try {
            User result = this.jdbcTemplate.queryForObject(
                    "SELECT * FROM user WHERE id = ?", userRowMapper, id);
            System.out.println(result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println("User not found with ID: " + id);
            return null;
        }
    }
}
