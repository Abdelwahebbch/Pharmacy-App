package com.pharmacy.Model;

import java.time.LocalDate;

public class User {
    private int id;
    private String username;
    private String fullName;
    private String email;
    private String role;
    private boolean active;
    private LocalDate lastLogin;
    
    public User(int id, String username, String fullName, String email, String role, boolean active, LocalDate lastLogin) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.active = active;
        this.lastLogin = lastLogin;
    }
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    
    public LocalDate getLastLogin() { return lastLogin; }
    public void setLastLogin(LocalDate lastLogin) { this.lastLogin = lastLogin; }
}
