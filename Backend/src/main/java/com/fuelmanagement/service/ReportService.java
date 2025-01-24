package com.fuelmanagement.service;

import com.fuelmanagement.model.entity.mysql.FuelQuotaTracker;
import com.fuelmanagement.model.entity.mysql.User;
import com.fuelmanagement.model.entity.mysql.Vehicle;
import com.fuelmanagement.repository.mysql.UserRepository;
import com.fuelmanagement.repository.mysql.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public Float fuelUsageForUser(Long userId) {
        // Fetch the user by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        // Get all vehicles owned by the user
        List<Vehicle> vehicles = vehicleRepository.findByUserId(userId);

        // Calculate total fuel usage for the user
        float totalFuelUsage = 0.0f;
        for (Vehicle vehicle : vehicles) {
            FuelQuotaTracker tracker = vehicle.getFuelQuotaTracker();
            if (tracker != null) {
                float fuelUsed = (float) (tracker.getWeeklyConsumption() - tracker.getExistingFuel());
                totalFuelUsage += fuelUsed;
            }
        }

        return totalFuelUsage;
    }
}

