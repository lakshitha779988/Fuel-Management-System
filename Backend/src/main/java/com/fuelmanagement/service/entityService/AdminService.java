package com.fuelmanagement.service.entityService;


import com.fuelmanagement.model.entity.mysql.FuelStation;
import com.fuelmanagement.repository.mysql.FuelStationRepository;
import com.fuelmanagement.service.notificationService.NotificationContetCreationService;
import com.fuelmanagement.service.notificationService.NotificationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminService {

   private final FuelStationRepository fuelStationRepository;
private final NotificationManager notificationManager;
private final NotificationContetCreationService notificationContetCreationService;


   @Autowired
    public AdminService( FuelStationRepository fuelStationRepository, NotificationManager notificationManager, NotificationContetCreationService notificationContetCreationService) {
        this.fuelStationRepository = fuelStationRepository;
       this.notificationManager = notificationManager;
       this.notificationContetCreationService = notificationContetCreationService;
   }



    public void changeFuelStationStatus(Long id) {
        if (!fuelStationRepository.existsById(id)) {
            throw new UsernameNotFoundException("Fuel station cannot be found");
        }

        FuelStation fuelStation = fuelStationRepository.findById(id).get();
        String currentStatus = fuelStation.getStatus();
        String newStatus = "Active".equalsIgnoreCase(currentStatus) ? "Blocked" : "Active";

        fuelStation.setStatus(newStatus);
        fuelStationRepository.save(fuelStation);

        LocalDateTime localDateTime = LocalDateTime.now();
        String content;
        String subject;

        if ("Active".equalsIgnoreCase(newStatus)) {
            content = notificationContetCreationService.generateFuelStationAccountActiveEmailContent(fuelStation.getName(), localDateTime);
            subject = "Fuel Station Activation";
        } else {
            content = notificationContetCreationService.generateFuelStationAccountBlockedEmailContent(fuelStation.getName(), localDateTime);
            subject = "Fuel Station Blocked";
        }

        notificationManager.notifyUser(fuelStation.getEmail(),fuelStation.getMobileNumber(), content);
    }



}
