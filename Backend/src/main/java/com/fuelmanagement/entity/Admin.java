package com.fuelmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role; // e.g., SUPER_ADMIN, MODERATOR

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

//User ↔ Vehicle: One-to-Many (A user can have multiple vehicles).
//Vehicle ↔ Vehicle Type: Many-to-One (Each vehicle has a type).
//Vehicle ↔ QR Code: One-to-One (Each vehicle has one QR code).
//Fuel Station ↔ Fuel Distribution: One-to-Many (A station can distribute fuel to multiple vehicles).
//Vehicle ↔ Fuel Distribution: Many-to-One (A vehicle can receive fuel from different stations).
//Admin ↔ All Entities: Admins manage users, fuel stations, and vehicles.
