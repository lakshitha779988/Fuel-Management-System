package com.fuelmanagement.service;

import com.fuelmanagement.model.entity.VehicleType;
import com.fuelmanagement.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VehicleTypeService {
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    // Create or update a vehicle type
    public VehicleType saveVehicleType(VehicleType vehicleType) {
        return vehicleTypeRepository.save(vehicleType);
    }

    // Fetch all vehicle types
    public List<VehicleType> getAllVehicleTypes() {
        return vehicleTypeRepository.findAll();
    }

    // Fetch a vehicle type by name
    public VehicleType getVehicleTypeByName(String VehicleTypeName) {
        return vehicleTypeRepository.findByTypeName(VehicleTypeName);
    }
}
