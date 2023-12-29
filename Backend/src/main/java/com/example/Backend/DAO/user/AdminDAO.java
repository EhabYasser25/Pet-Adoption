package com.example.Backend.DAO.user;

import com.example.Backend.model.user.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Admin getById(int id) {
        try {
            BeanPropertyRowMapper<Admin> rowMapper = new BeanPropertyRowMapper<>(Admin.class);
            Admin admin = this.jdbcTemplate.queryForObject(
                    "SELECT * FROM admin WHERE id = ?", rowMapper, id);
            return admin;
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Admin not found with ID: " + id);
            return null;
        }
    }

    public Admin getByUsername(String username) {
        try {
            String query = "SELECT * FROM admin WHERE username = ? ";
            BeanPropertyRowMapper<Admin> rowMapper = new BeanPropertyRowMapper<>(Admin.class);
            Admin admin = this.jdbcTemplate.queryForObject(query, rowMapper, username);
            return admin;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean insertAdmin(Admin admin) {
        try {
            String insertQuery = "INSERT INTO admin (username, password) VALUES (?, ?)";
            int rowsAffected = jdbcTemplate.update(insertQuery, admin.getUsername(), admin.getPassword());

            return rowsAffected > 0;
        } catch (DataAccessException e) {
            // Handle database-related exceptions
            e.printStackTrace();
            return false;
        }
    }
}