package com.fuelmanagement.service.registerService;

import com.fuelmanagement.model.dto.request.RegistrationRequest;
import com.fuelmanagement.model.entity.mysql.FuelQuotaTracker;
import com.fuelmanagement.model.entity.mysql.User;
import com.fuelmanagement.model.entity.mysql.Vehicle;
import com.fuelmanagement.model.entity.mysql.VehicleType;
import com.fuelmanagement.repository.mysql.FuelQuotaTrackerRepository;
import com.fuelmanagement.repository.mysql.UserRepository;
import com.fuelmanagement.repository.mysql.VehicleRepository;
import com.fuelmanagement.repository.mysql.VehicleTypeRepository;
import com.fuelmanagement.service.notificationService.NotificationContetCreationService;
import com.fuelmanagement.service.notificationService.NotificationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;


@Service
public class FuelUserRegisterService implements RegisterService<RegistrationRequest>{

    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final FuelQuotaTrackerRepository fuelQuotaTrackerRepository;
    private final NotificationManager notificationManager;
    private final NotificationContetCreationService notificationContetCreationService;
    private final VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    public FuelUserRegisterService(UserRepository userRepository, VehicleRepository vehicleRepository, FuelQuotaTrackerRepository fuelQuotaTrackerRepository, NotificationManager notificationManager, NotificationContetCreationService notificationContetCreationService, VehicleTypeRepository vehicleTypeRepository) {
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
        this.fuelQuotaTrackerRepository = fuelQuotaTrackerRepository;
        this.notificationManager = notificationManager;
        this.notificationContetCreationService = notificationContetCreationService;

        this.vehicleTypeRepository = vehicleTypeRepository;
    }


    @Override
    public String register(RegistrationRequest registrationRequest) {
        if (userRepository.existsByMobileNumber(registrationRequest.getMobileNumber())) {
            throw new IllegalArgumentException("Mobile number already registered.");
        }

        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new IllegalArgumentException("Email address is already registered.");
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
        vehicle.setCreatedAt(new Date());
        vehicle.setFuelType(registrationRequest.getFuelType());
        vehicle.setFuelQuotaTracker(fuelQuotaTracker1);
        vehicle.setVehicleType(vehicleType);
        vehicleRepository.save(vehicle);

        // Create and save User
        User user = new User();
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setMobileNumber(registrationRequest.getMobileNumber());
        user.setNationalId(registrationRequest.getNationalId());
        user.setCreatedAt(LocalDateTime.now());
        user.setVehicle(vehicle);
        user.setEmail(registrationRequest.getEmail());
        user.setRole("citizen");
        userRepository.save(user);


        System.out.println("User and Vehicle registered successfully.");
        String content = notificationContetCreationService.generateUserRegistrationEmailContent(user.getFirstName(),user.getMobileNumber(),vehicle.getRegistrationNumber());
        notificationManager.notifyUser(user.getEmail(), user.getMobileNumber(),content);

        return "Sucsuss";
    }




}

