package com.fuelmanagement.controller;


import com.fuelmanagement.model.dto.response.FuelTransactionDTO;
import com.fuelmanagement.service.JwtService;
import com.fuelmanagement.service.ReportService;
import com.fuelmanagement.service.entityService.FuelLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/report")

public class ReportController {
    @Autowired
    ReportService reportService;

    @Autowired
    JwtService jwtService;

    @Autowired
    FuelLogService fuelLogService;

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



    @GetMapping("/user/transaction")
    public ResponseEntity<List<FuelTransactionDTO>> getLast10Transactions(@RequestHeader("Authorization") String authHeader){
        try {
            // Extract JWT Token (Remove "Bearer " prefix)
            String token = authHeader.replace("Bearer ", "");

            // Optional: Decode JWT to extract user details
            String mobileNumber = jwtService.extractIdentifier(token); // Assuming `jwtUtil` has a method to decode JWT

            // Fetch fuel usage
            List<FuelTransactionDTO> transactions = fuelLogService.getLast10Transactions(mobileNumber);
            return ResponseEntity.ok(transactions);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }







   



}