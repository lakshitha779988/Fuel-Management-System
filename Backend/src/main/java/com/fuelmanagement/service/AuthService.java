package com.fuelmanagement.service;

import com.fuelmanagement.model.dto.request.LoggingRequest;
import com.fuelmanagement.model.dto.request.RegistrationRequest;
import com.fuelmanagement.model.entity.User;
import com.fuelmanagement.model.entity.Vehicle;
import com.fuelmanagement.model.entity.VehicleType;
import com.fuelmanagement.repository.UserRepository;
import com.fuelmanagement.repository.VehicleRepository;
import com.fuelmanagement.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleTypeRepository vehicleTypeRepository;


    @Autowired
    public AuthService(UserRepository userRepository,
                       VehicleRepository vehicleRepository,
                       VehicleTypeRepository vehicleTypeRepository
                       ) {
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;

    }

    public void register(RegistrationRequest registrationRequest) {
        // Check if user with the given mobileNumber or nationalId already exists
        if (userRepository.existsByMobileNumber(registrationRequest.getMobileNumber())) {
            throw new IllegalArgumentException("Mobile number already registered.");
        }

        if (userRepository.existsByNationalId(registrationRequest.getNationalId())) {
            throw new IllegalArgumentException("National ID already registered.");
        }

        // Check if the vehicle is already registered
        if (vehicleRepository.existsByRegistrationNumber(registrationRequest.getVehicleNumber())) {
            throw new IllegalArgumentException("Vehicle with this registration number is already registered.");
        }

        // Check if the vehicle number is valid (Placeholder logic for now)
        // Uncomment and implement actual validation logic later if needed
        /*
        if (!isValidVehicleNumber(registrationRequest.getVehicleNumber())) {
            throw new IllegalArgumentException("Invalid vehicle number.");
        }
        */

        // Find VehicleType by ID
        Optional<VehicleType> vehicleTypeOptional = vehicleTypeRepository.findById(registrationRequest.getVehicleTypeId());
        if (vehicleTypeOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid Vehicle Type ID.");
        }
        VehicleType vehicleType = vehicleTypeOptional.get();

        // Create and save User
        User user = new User();
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setMobileNumber(registrationRequest.getMobileNumber());
        user.setNationalId(registrationRequest.getNationalId());
        user.setPassword(registrationRequest.getPassword());
        user.setCreateAt(new Date());
        userRepository.save(user);

        // Create and save Vehicle
        Vehicle vehicle = new Vehicle();
        vehicle.setChaseNumber(registrationRequest.getChaseNumber());
        vehicle.setRegistrationNumber(registrationRequest.getVehicleNumber());
        vehicle.setVehicleType(vehicleType);
        vehicle.setCreatedAt(new Date());
        vehicleRepository.save(vehicle);

        // Log success or return a response DTO
        System.out.println("User and Vehicle registered successfully.");
    }

    // Placeholder for vehicle number validation
    private boolean isValidVehicleNumber(String vehicleNumber) {
        // Add validation logic here
        return true; // Placeholder logic; replace with actual checks
    }



public void logging(LoggingRequest loggingRequest) {

    }
}
