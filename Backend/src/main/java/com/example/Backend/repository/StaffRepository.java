package com.example.Backend.repository;

import com.example.Backend.model.Staff;

import java.sql.SQLException;

public interface StaffRepository {
    void addStaff(Staff staff) throws SQLException;
}
