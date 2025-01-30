package com.fuelmanagement.service.entityService;

import com.fuelmanagement.model.entity.mysql.VehicleType;
import com.fuelmanagement.repository.mysql.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    public VehicleTypeService(VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

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
        return vehicleTypeRepository.findByVehicleTypeName(VehicleTypeName);
    }
}
