package com.example.Backend.mapper.user;

import com.example.Backend.model.user.Admin;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AdminRowMapper implements RowMapper<Admin> {
    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Admin(
                rs.getString("username"),
                rs.getString("password")
        );
    }
}
