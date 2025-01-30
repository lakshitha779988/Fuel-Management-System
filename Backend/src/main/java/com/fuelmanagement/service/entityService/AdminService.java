package com.fuelmanagement.service.entityService;


import com.fuelmanagement.model.dto.request.AdminLoginRequest;
import com.fuelmanagement.model.entity.mysql.Admin;
import com.fuelmanagement.model.entity.mysql.FuelStation;
import com.fuelmanagement.repository.mysql.AdminRepository;
import com.fuelmanagement.repository.mysql.FuelStationRepository;
import com.fuelmanagement.service.EmailService;
import com.fuelmanagement.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
   private final JwtService jwtService;
   private final FuelStationRepository fuelStationRepository;
   private final EmailService emailService;


   @Autowired
    public AdminService(AdminRepository adminRepository, JwtService jwtService, FuelStationRepository fuelStationRepository, EmailService emailService) {
        this.adminRepository = adminRepository;
        this.jwtService = jwtService;
        this.fuelStationRepository = fuelStationRepository;
        this.emailService = emailService;
    }


    public String login(AdminLoginRequest adminLoginRequest) {
        Admin admin = adminRepository.findByUserName(adminLoginRequest.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        if (!Objects.equals(adminLoginRequest.getPassword(), admin.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        String token = jwtService.generateToken(admin.getUserName(), "FUEL_STATION", "Admin");

        return token;
    }

    public void changeFuelStationStatus(Long id, String status) {
        if (!fuelStationRepository.existsById(id)) {
            throw new UsernameNotFoundException("Fuel station cannot be found");
        }

        FuelStation fuelStation = fuelStationRepository.findById(id).get();
        fuelStation.setStatus(status);
        fuelStationRepository.save(fuelStation);

        LocalDateTime localDateTime = LocalDateTime.now();
        String emailContent;
        String subject;

        if ("Active".equalsIgnoreCase(status)) {
            emailContent = emailService.generateFuelStationAccountActiveEmailContent(fuelStation.getName(), localDateTime);
            subject = "Fuel Station Activation";
        } else if ("Blocked".equalsIgnoreCase(status)) {
            emailContent = emailService.generateFuelStationAccountBlockedEmailContent(fuelStation.getName(), localDateTime);
            subject = "Fuel Station Blocked";
        } else {
            throw new IllegalArgumentException("Invalid status: " + status);
        }

        emailService.sendEmail(fuelStation.getEmail(), subject, emailContent);
    }


}
