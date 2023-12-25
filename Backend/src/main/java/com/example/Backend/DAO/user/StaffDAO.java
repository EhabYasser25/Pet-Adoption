package com.example.Backend.DAO.user;

import com.example.Backend.mapper.user.StaffRowMapper;
import com.example.Backend.mapper.user.UserRowMapper;
import com.example.Backend.model.user.Staff;
import com.example.Backend.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StaffDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StaffRowMapper staffRowMapper;

    public Staff getById(int id) {
        try {
            Staff result = this.jdbcTemplate.queryForObject(
                    "SELECT * FROM staff WHERE user_id = ?", staffRowMapper, id);
            System.out.println(result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println("Staff not found with ID: " + id);
            return null;
        }
    }
}
