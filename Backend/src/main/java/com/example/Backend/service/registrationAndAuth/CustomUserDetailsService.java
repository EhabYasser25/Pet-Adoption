package com.example.Backend.service.registrationAndAuth;

import com.example.Backend.DAO.user.AdminDAO;
import com.example.Backend.DAO.user.UserDAO;
import com.example.Backend.model.user.Admin;
import com.example.Backend.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    AdminDAO adminDAO;

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDAO.getByUsernameOrEmail(username);

        Admin admin = adminDAO.getByUsername(username);

        System.out.println(admin.getPassword());
        System.out.println(admin.getUsername());
        if (user == null && admin == null)
            throw new UsernameNotFoundException("User not found");

        return user == null ? admin : user;
    }
}
