package com.fuelmanagement.service.entityService;

import com.fuelmanagement.model.dto.request.VehicleTypeAddRequest;
import com.fuelmanagement.model.entity.mysql.VehicleType;
import com.fuelmanagement.repository.mysql.VehicleTypeRepository;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    public VehicleTypeService(VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    // Create or update a vehicle type
    public String saveVehicleType(VehicleTypeAddRequest vehicleTypeAddRequest) {

        if(vehicleTypeAddRequest.getQuota()<=0){
            throw new IllegalArgumentException("Fuel Quota is not valid");
        }

        VehicleType vehicleType = new VehicleType();
        vehicleType.setCreatedAt(new Date(System.currentTimeMillis()));
        vehicleType.setVehicleTypeName(vehicleTypeAddRequest.getName());
        vehicleType.setFuelLimit(vehicleTypeAddRequest.getQuota());

        vehicleTypeRepository.save(vehicleType);
        return "Vehicle type added susussfully";
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
