package com.example.Backend.service;

import com.example.Backend.DAO.UserDAO;
import com.example.Backend.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public User getUserById(int id) {
        return userDAO.getUserById(3);
    }
}
