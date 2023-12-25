package com.example.Backend.service.user;

import com.example.Backend.DAO.user.AdminDAO;
import com.example.Backend.model.user.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminDAO adminDAO;

    public Admin getByUsername(String username) {
        return adminDAO.getByUsername(username);
    }
}
