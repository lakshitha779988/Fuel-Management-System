package com.fuelmanagement.service;

import com.fuelmanagement.model.entity.Vehicle;
import com.fuelmanagement.repository.VehicleRepository;
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
    }

