package com.example.Backend.mapper.user;

import com.example.Backend.model.user.Staff;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StaffRowMapper implements RowMapper<Staff> {
    @Override
    public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Staff(
                rs.getInt("user_id"),
                rs.getInt("shelter_id")
        );
    }
}
