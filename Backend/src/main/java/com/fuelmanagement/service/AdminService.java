package com.fuelmanagement.service;

import com.fuelmanagement.entity.Admin;
import com.fuelmanagement.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin registerAdmin(Admin admin) {
        if (adminRepository.findByUsername(admin.getUsername()).isPresent()) {
            throw new RuntimeException("Admin username already exists");
        }
        return adminRepository.save(admin);
    }

    public Admin login(String username, String password) {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        if (!admin.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }
        return admin;
    }
}