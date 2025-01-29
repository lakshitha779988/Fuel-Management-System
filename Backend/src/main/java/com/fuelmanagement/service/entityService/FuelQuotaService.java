package com.fuelmanagement.service.entityService;

import com.fuelmanagement.model.entity.mysql.*;
import com.fuelmanagement.repository.mysql.*;
import com.fuelmanagement.service.EmailService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class FuelQuotaService {

    private final QrCodeRepository qrCodeRepository;
    private final VehicleRepository vehicleRepository;
    private final FuelQuotaTrackerRepository fuelQuotaTrackerRepository;
    private final FuelStationRepository fuelStationRepository;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final FuelLogRepository fuelLogRepository;

    public FuelQuotaService(QrCodeRepository qrCodeRepository,
                            VehicleRepository vehicleRepository,
                            FuelQuotaTrackerRepository fuelQuotaTrackerRepository, FuelStationRepository fuelStationRepository, EmailService emailService, UserRepository userRepository, FuelLogRepository fuelLogRepository) {
        this.qrCodeRepository = qrCodeRepository;
        this.vehicleRepository = vehicleRepository;
        this.fuelQuotaTrackerRepository = fuelQuotaTrackerRepository;
        this.fuelStationRepository = fuelStationRepository;
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.fuelLogRepository = fuelLogRepository;
    }

    public String updateFuelLimit(String qrString, float usage, Long fuelStationId) {
        // Validate QR code
        if (!qrCodeRepository.existsByQrCode(qrString)) {
            throw new IllegalArgumentException("Invalid QR code: " + qrString);
        }

        if(!fuelStationRepository.existsById(fuelStationId)){
            throw new IllegalArgumentException("Fuel station id is not valid");
        }

        FuelStation fuelStation = fuelStationRepository.findById(fuelStationId).get();
        QrCode qrCode = qrCodeRepository.findByQrCode(qrString).get();
        Long qrCodeId = qrCode.getId();
        System.out.println(qrCodeId);


        Vehicle vehicle = qrCode.getVehicle();

       User user =  userRepository.findByVehicleId(vehicle.getId()).get();



        // Validate fuel quota tracker
        FuelQuotaTracker fuelQuotaTracker = vehicle.getFuelQuotaTracker();
        if (fuelQuotaTracker == null) {
            throw new IllegalStateException("Fuel quota tracker not found for the associated vehicle.");
        }

        Long fuelQuotaTrackerId = fuelQuotaTracker.getId();
        Optional<FuelQuotaTracker> trackerOptional = fuelQuotaTrackerRepository.findById(fuelQuotaTrackerId);

        if (trackerOptional.isEmpty()) {
            throw new IllegalStateException("Fuel quota tracker record does not exist.");
        }

        // Update fuel limit
        FuelQuotaTracker tracker = trackerOptional.get();
        float currentFuel = tracker.getExistingFuel();
        if (currentFuel < usage) {
            return "Insufficient fuel quota. Available: " + currentFuel + " liters.";
        }

        tracker.setExistingFuel(currentFuel - usage);
        fuelQuotaTrackerRepository.save(tracker);

        float updateExsistingFuel = tracker.getExistingFuel();
        LocalDateTime localDateTime = LocalDateTime.now();



        //Update Fuel Log about this transaction
        FuelLog fuelLog = new FuelLog();
        fuelLog.setFuelAmount(usage);
        fuelLog.setFuelStation(fuelStation);
        fuelLog.setUser(user);
        fuelLog.setVehicle(vehicle);
        fuelLog.setCreatedAt(new Date(System.currentTimeMillis()));
        fuelLog.setTransactionTime(new Date(System.currentTimeMillis()));

        fuelLogRepository.save(fuelLog);

        String emailContent = emailService.generateFuelLimitUpdateEmailContent(fuelStation.getName(),usage,localDateTime,updateExsistingFuel);
        emailService.sendEmail(user.getEmail(),"Fuel Update" , emailContent);


        return "Fuel limit updated successfully. Remaining quota: " + tracker.getExistingFuel() + " liters.";
    }
}
