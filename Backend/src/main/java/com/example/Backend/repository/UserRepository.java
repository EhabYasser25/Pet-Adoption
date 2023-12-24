package com.example.Backend.repository;

import com.example.Backend.DAO.User;

import java.sql.SQLException;

public interface UserRepository {
    void addUser(User user) throws SQLException;
    User getUserById(Long id) throws SQLException;
}
