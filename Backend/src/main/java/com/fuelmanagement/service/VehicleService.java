package com.fuelmanagement.service;

import com.fuelmanagement.model.entity.mysql.Vehicle;
import com.fuelmanagement.repository.mysql.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    public class VehicleService {
        @Autowired
        private VehicleRepository vehicleRepository;

        public Vehicle registerVehicle(Vehicle vehicle) {
            // Add validation logic (e.g., mock Motor Traffic DB validation)
            return vehicleRepository.save(vehicle);
        }


        
        public Vehicle getVehicleById(Long vehicleId) {
            // Fetch the vehicle details using its ID
            return vehicleRepository.findById(vehicleId)
                    .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with ID: " + vehicleId));
        }
    }

