package com.example.Backend.repository;

import com.example.Backend.DAO.Admin;

import java.sql.SQLException;

public interface AdminRepository {
    void addAdmin(Admin admin) throws SQLException;
    Admin getAdminByUsername(String username) throws SQLException;
}
