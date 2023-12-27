package com.example.Backend.service.registrationAndAuth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // To do

        // check if username exists in admin

        // check if it exists in user

        // else throw exception

        // return user or admin models they should implement the userDetails

        return null;
    }
}
