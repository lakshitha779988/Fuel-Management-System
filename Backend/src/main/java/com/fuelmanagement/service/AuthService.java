package com.fuelmanagement.service;

import com.fuelmanagement.model.dto.request.RegistrationRequest;
import com.fuelmanagement.model.dto.response.LoginResponse;
import com.fuelmanagement.model.entity.mysql.*;
import com.fuelmanagement.repository.mysql.*;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final JwtService jwtService;
    private final SmsService smsService;
    private final FuelQuotaTrackerRepository fuelQuotaTrackerRepository;
    private final FuelStationRepository fuelStationRepository;
    private final PasswordEncoder passwordEncoder;

    private FirebaseTokenService firebaseTokenService;
    private UserService userService;

    public AuthService(UserRepository userRepository, VehicleRepository vehicleRepository, VehicleTypeRepository vehicleTypeRepository, JwtService jwtService, SmsService smsService, FuelQuotaTrackerRepository fuelQuotaTrackerRepository, FuelStationRepository fuelStationRepository, PasswordEncoder passwordEncoder, FirebaseTokenService firebaseTokenService, UserService userService) {
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.jwtService = jwtService;
        this.smsService = smsService;
        this.fuelQuotaTrackerRepository = fuelQuotaTrackerRepository;
        this.fuelStationRepository = fuelStationRepository;
        this.passwordEncoder = passwordEncoder;
        this.firebaseTokenService = firebaseTokenService;
        this.userService = userService;
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

        //create fuel-quota tracker

        FuelQuotaTracker fuelQuotaTracker1 = new FuelQuotaTracker();
        fuelQuotaTracker1.setExistingFuel((float) vehicleType.getFuelLimit());
        fuelQuotaTracker1.setWeeklyConsumption((float) vehicleType.getFuelLimit());
        fuelQuotaTracker1.setCreatedAt(new Date(System.currentTimeMillis()));
        fuelQuotaTracker1.setResetDate(new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000));
        fuelQuotaTrackerRepository.save(fuelQuotaTracker1);





        // Create and save Vehicle
        Vehicle vehicle = new Vehicle();
        vehicle.setChaseNumber(registrationRequest.getChaseNumber());
        vehicle.setRegistrationNumber(registrationRequest.getVehicleNumber());
        vehicle.setVehicleType(vehicleType);
        vehicle.setCreatedAt(new Date());
        vehicle.setFuelType(registrationRequest.getFuelType());
        vehicle.setFuelQuotaTracker(fuelQuotaTracker1);
        vehicleRepository.save(vehicle);

        // Create and save User
        User user = new User();
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setMobileNumber(registrationRequest.getMobileNumber());
        user.setNationalId(registrationRequest.getNationalId());
        user.setPassword(registrationRequest.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        user.setVehicle(vehicle);
        userRepository.save(user);


        System.out.println("User and Vehicle registered successfully.");
    }


    private boolean isValidVehicleNumber(String vehicleNumber) {
        //need conect with mock database and check
        return true; // Placeholder logic; replace with actual checks
    }




    public LoginResponse authenticateFuelUser(String mobileNumber, String firebaseToken) {
        try {

            FirebaseToken decodedToken = firebaseTokenService.verifyToken(firebaseToken);


            User fuelUser = userRepository.findByMobileNumber(mobileNumber)
                    .orElseThrow(() -> new UsernameNotFoundException("Mobile number not registered"));

            // Step 3: Generate JWT token
            String token = jwtService.generateToken(fuelUser.getMobileNumber(), "FUEL_USER", fuelUser.getRole());

            // Step 4: Return response
            return new LoginResponse(token, "FUEL_USER", null, mobileNumber, fuelUser.getRole());
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Invalid Firebase token", e);
        }
    }


    public LoginResponse authenticateFuelStation(String username, String password) {
        // Step 1: Find the fuel station by username
        FuelStation station = fuelStationRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        // Step 2: Verify the password
        if (!passwordEncoder.matches(password, station.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        // Step 3: Generate JWT token
        String token = jwtService.generateToken(station.getName(), "FUEL_STATION", station.getRole());

        // Step 4: Return response
        return new LoginResponse(token, "FUEL_STATION", username, null, station.getRole());
    }


}
