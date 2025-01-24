package com.fuelmanagement.service;

import com.fuelmanagement.model.dto.request.FuelStationRequest;
import com.fuelmanagement.model.entity.mysql.FuelStation;
import com.fuelmanagement.repository.mysql.FuelStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuelStationService {


    private final FuelStationRepository fuelStationRepository;


    @Autowired
    public FuelStationService(FuelStationRepository fuelStationRepository) {
        this.fuelStationRepository = fuelStationRepository;

    }

    public String registerFuelStation(FuelStationRequest fuelStationRequest) {
        // Validate input
        if (fuelStationRequest.getName() == null || fuelStationRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("Fuel station name is required.");
        }
        if (fuelStationRequest.getEmail() == null || fuelStationRequest.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required.");
        }
        if (fuelStationRequest.getPassword() == null || fuelStationRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required.");
        }

        // Encrypt password


        // Create and save the fuel station
        FuelStation fuelStation = new FuelStation();
        fuelStation.setEmail(fuelStationRequest.getEmail());
        fuelStation.setMobileNumber(fuelStationRequest.getMobileNumber());
        fuelStation.setName(fuelStationRequest.getName());
        fuelStation.setPassword(fuelStationRequest.getPassword());

        fuelStationRepository.save(fuelStation);

        return "Fuel station registered successfully: " + fuelStation.getName();
    }
}
