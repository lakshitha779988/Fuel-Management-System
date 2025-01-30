package com.fuelmanagement.controller;


import com.fuelmanagement.service.JwtService;
import com.fuelmanagement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")

public class ReportController {
    @Autowired
    ReportService reportService;

    @Autowired
    JwtService jwtService;

    @GetMapping("/user/fuel-usage")
    public ResponseEntity<Float> fuelUsageForUser(@RequestHeader("Authorization") String authHeader) {
        try {
            // Extract JWT Token (Remove "Bearer " prefix)
            String token = authHeader.replace("Bearer ", "");

            // Optional: Decode JWT to extract user details
            String mobileNumber = jwtService.extractIdentifier(token); // Assuming `jwtUtil` has a method to decode JWT

            // Fetch fuel usage
            Float totalFuelUsage = reportService.fuelUsageForUser(mobileNumber);
            return ResponseEntity.ok(totalFuelUsage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Float.valueOf(e.getMessage()));
        }

    }

    @GetMapping("/user/exsistingFuel")
    public ResponseEntity<Float> exsistingFuelAmount(@RequestHeader("Authorization") String authHeader){
        try {
            // Extract JWT Token (Remove "Bearer " prefix)
            String token = authHeader.replace("Bearer ", "");

            // Optional: Decode JWT to extract user details
            String mobileNumber = jwtService.extractIdentifier(token); // Assuming `jwtUtil` has a method to decode JWT

            // Fetch fuel usage
            Float totalFuelUsage = reportService.exsistingFuelForUser(mobileNumber);
            return ResponseEntity.ok(totalFuelUsage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Float.valueOf(e.getMessage()));
        }
    }







   



}