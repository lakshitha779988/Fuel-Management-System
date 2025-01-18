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

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final JwtService jwtService;


    @Autowired
    public AuthService(UserRepository userRepository,
                       VehicleRepository vehicleRepository,
                       VehicleTypeRepository vehicleTypeRepository, JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;

        this.jwtService = jwtService;
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


        System.out.println("User and Vehicle registered successfully.");
    }


    private boolean isValidVehicleNumber(String vehicleNumber) {
        //need conect with mock database and check
        return true; // Placeholder logic; replace with actual checks
    }



    public String  login(LoggingRequest loggingRequest) {

        //check mobile number is exit on database or not
        User user = userRepository.findByMobileNumber(loggingRequest.getMobileNumber());
        if (user == null) {
            throw new RuntimeException("Mobile number not found.");
        }

        //generate otm
        String otp = generateOtp();
        LocalDateTime otpExpiry = LocalDateTime.now().plus(5, ChronoUnit.MINUTES);  // 5 minutes from now

        //update otp in the user table for temporally
        user.setOtp(otp);
        user.setOtpExpiry(otpExpiry);

        userRepository.save(user);

        //implement sms service

        return "OTP sent to registered mobile number.";


    }


    public String verifyOtp(String mobileNumber, String inputOtp) {

        //  Check if the user on that mobile number exists
        User user = userRepository.findByMobileNumber(mobileNumber);

        // Validate OTP is valid or not
        if (!user.getOtp().equals(inputOtp)) {
            throw new RuntimeException("Invalid OTP");
        }
        if (user.getOtpExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        // Generate JWT Token
        String token = jwtService.generateToken(mobileNumber);

        // Clear OTP in user table
        user.setOtp(null);
        user.setOtpExpiry(null);
        userRepository.save(user);

        return token;
    }




    private String generateOtp() {
        Random rand = new Random();
        int otp = rand.nextInt(900000) + 100000; // Generates a 6-digit OTP
        return String.valueOf(otp);
    }

}
