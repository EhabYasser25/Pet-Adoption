package com.example.Backend.service;

import com.example.Backend.DAO.user.UserDAO;
import com.example.Backend.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public User getById(int id) {
        return userDAO.getById(id);
    }
}
