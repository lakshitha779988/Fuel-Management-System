package com.fuelmanagement.service.registerService;

import com.fuelmanagement.model.dto.request.FuelStationRequest;
import com.fuelmanagement.model.entity.mysql.FuelStation;
import com.fuelmanagement.repository.mysql.FuelStationRepository;
import com.fuelmanagement.service.notificationService.NotificationContetCreationService;
import com.fuelmanagement.service.notificationService.NotificationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FuelStationRegisterService implements RegisterService<FuelStationRequest>{

    private final FuelStationRepository fuelStationRepository;
   private final NotificationManager notificationManager;
   private final NotificationContetCreationService notificationContetCreationService;

    @Autowired
    public FuelStationRegisterService(FuelStationRepository fuelStationRepository, NotificationManager notificationManager, NotificationContetCreationService notificationContetCreationService) {
        this.fuelStationRepository = fuelStationRepository;
        this.notificationManager = notificationManager;

        this.notificationContetCreationService = notificationContetCreationService;
    }


    @Override
    public String register(FuelStationRequest fuelStationRequest) {
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
        fuelStation.setStock(0);
        fuelStation.setStatus("Blocked");

        fuelStationRepository.save(fuelStation);
        String content = notificationContetCreationService.generateFuelStationRegistrationEmailContent(fuelStation.getName());
        notificationManager.notifyUser(fuelStation.getEmail(),fuelStation.getMobileNumber(), content);

        return "Fuel station registered successfully: " + fuelStation.getName();

    }
}
