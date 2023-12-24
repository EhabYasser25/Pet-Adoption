package com.example.Backend.service;

import com.example.Backend.DAO.Admin;
import com.example.Backend.config.DatabaseConnection;
import com.example.Backend.repository.AdminRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepositoryJDBC implements AdminRepository {
    @Override
    public void addAdmin(Admin admin) throws SQLException {
        String sql = "INSERT INTO ADMIN (username, password, role, user_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, admin.getUsername());
            pstmt.setString(2, admin.getPassword());
            pstmt.setString(3, admin.getRole());
            pstmt.setLong(4, admin.getUserId()); // Set the foreign key reference
            pstmt.executeUpdate();
        }
    }

    @Override
    public Admin getAdminByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM ADMIN WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Admin admin = new Admin();
                    admin.setUsername(rs.getString("username"));
                    admin.setPassword(rs.getString("password"));
                    admin.setRole(rs.getString("role"));
                    admin.setUserId(rs.getLong("user_id")); // Retrieve the foreign key
                    return admin;
                }
            }
        }
        return null;
    }

}
