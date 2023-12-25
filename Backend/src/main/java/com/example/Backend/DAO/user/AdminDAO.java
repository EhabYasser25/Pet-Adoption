package com.example.Backend.DAO.user;

import com.example.Backend.mapper.user.AdminRowMapper;
import com.example.Backend.model.user.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AdminRowMapper adminRowMapper;

    public Admin getByUsername(String username) {
        try {
            Admin result = this.jdbcTemplate.queryForObject(
                    "SELECT * FROM admin WHERE username = ?", adminRowMapper, username);
            System.out.println(result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println("Admin not found with username: " + username);
            return null;
        }
    }
}
