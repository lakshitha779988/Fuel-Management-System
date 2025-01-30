package com.fuelmanagement.service.entityService;

import com.fuelmanagement.model.dto.request.FuelStationRequest;
import com.fuelmanagement.model.entity.mysql.FuelStation;
import com.fuelmanagement.repository.mysql.FuelStationRepository;
import com.fuelmanagement.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuelStationService {


    private final FuelStationRepository fuelStationRepository;
    private final EmailService emailService;

    @Autowired
    public FuelStationService(FuelStationRepository fuelStationRepository, EmailService emailService) {
        this.fuelStationRepository = fuelStationRepository;

        this.emailService = emailService;
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
        fuelStation.setRole("FuelStation");

        fuelStationRepository.save(fuelStation);
        String emailContent = emailService.generateFuelStationRegistrationEmailContent(fuelStation.getName());
        emailService.sendEmail(fuelStation.getEmail(),"FuelStation Registration Is Pending" , emailContent);

        return "Fuel station registered successfully: " + fuelStation.getName();

    }

    public Long getFuelStationIdByMobileNumber(String mobileNumber) {
        return fuelStationRepository.findByMobileNumber(mobileNumber).get().getId();
    }
}
