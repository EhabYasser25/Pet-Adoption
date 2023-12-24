package com.example.Backend.model;

public class Admin {
    private String username;
    private String password;
    private String role;
    private Long userId;

    // Getters for each field
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Long getUserId() {
        return userId;
    }

    // Setters for each field
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
