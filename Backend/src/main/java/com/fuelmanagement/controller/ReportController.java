package com.fuelmanagement.controller;


import com.fuelmanagement.repository.mysql.FuelLogRepository;
import com.fuelmanagement.repository.mysql.VehicleTypeRepository;
import com.fuelmanagement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")

public class ReportController {
    @Autowired
    ReportService reportService;

    @GetMapping("/user/{userId}/fuel-usage")
    public ResponseEntity<?> fuelUsageForUser(@PathVariable Long userId) {
        try {
            Float totalFuelUsage = reportService.fuelUsageForUser(userId);
            return ResponseEntity.ok("Total Fuel Usage for User ID " + userId + ": " + totalFuelUsage + " liters");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }


    public Float fuelUsageForEachVehicle( String registrationNumber) {

        Float FuelUsage = reportService.FuelUsageForEachVehicle(registrationNumber);

        return (FuelUsage);

    }

    public Float costUsageForEachVehicle(String registrationNumber) {

        Float CostUsage = reportService.CostUsageForEachVehicle(registrationNumber);

        return (CostUsage);
    }



   



}