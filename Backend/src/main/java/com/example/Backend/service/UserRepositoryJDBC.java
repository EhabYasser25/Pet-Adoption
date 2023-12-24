package com.example.Backend.service;

import com.example.Backend.model.User;
import com.example.Backend.config.DatabaseConnection;
import com.example.Backend.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryJDBC implements UserRepository {
    @Override
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO USER (firstName, lastName, username, email, ...) VALUES (?, ?, ?, ?, ...)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            // Set other parameters...
            pstmt.executeUpdate();
        }
    }

    @Override
    public User getUserById(Long id) throws SQLException {
        String sql = "SELECT * FROM USER WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setFirstName(rs.getString("firstName"));
                    // Set other fields...
                    return user;
                }
            }
        }
        return null;
    }

}
