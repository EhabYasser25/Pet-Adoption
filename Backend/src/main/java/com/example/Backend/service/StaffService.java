package com.example.Backend.service;

import com.example.Backend.DAO.user.StaffDAO;
import com.example.Backend.model.user.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    @Autowired
    private StaffDAO staffDAO;

    public Staff getById(int id) {
        return staffDAO.getById(id);
    }
}
