package com.example.Backend.service;

import com.example.Backend.config.DatabaseConnection;
import com.example.Backend.model.Staff;

import java.sql.*;

public class StaffRepositoryJDBC {

    public void addStaff(Staff staff) throws SQLException {
        String sql = "INSERT INTO STAFF (user_id, shelter_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, staff.getUserId());
            pstmt.setLong(2, staff.getShelterId());
            pstmt.executeUpdate();
        }
    }

}
