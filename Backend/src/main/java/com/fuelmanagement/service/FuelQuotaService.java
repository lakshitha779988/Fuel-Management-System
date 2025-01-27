package com.fuelmanagement.service;

import com.fuelmanagement.model.entity.mysql.FuelQuotaTracker;
import com.fuelmanagement.model.entity.mysql.QrCode;
import com.fuelmanagement.model.entity.mysql.Vehicle;
import com.fuelmanagement.repository.mysql.FuelQuotaTrackerRepository;
import com.fuelmanagement.repository.mysql.QrCodeRepository;
import com.fuelmanagement.repository.mysql.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuelQuotaService {

    private final QrCodeRepository qrCodeRepository;
    private final VehicleRepository vehicleRepository;
    private final FuelQuotaTrackerRepository fuelQuotaTrackerRepository;

    public FuelQuotaService(QrCodeRepository qrCodeRepository,
                            VehicleRepository vehicleRepository,
                            FuelQuotaTrackerRepository fuelQuotaTrackerRepository) {
        this.qrCodeRepository = qrCodeRepository;
        this.vehicleRepository = vehicleRepository;
        this.fuelQuotaTrackerRepository = fuelQuotaTrackerRepository;
    }

    public String updateFuelLimit(String qrString, float usage) {
        // Validate QR code
        if (!qrCodeRepository.existsByQrCode(qrString)) {
            throw new IllegalArgumentException("Invalid QR code: " + qrString);
        }

        QrCode qrCode = qrCodeRepository.findByQrCode(qrString).get();
        Long qrCodeId = qrCode.getId();
        System.out.println(qrCodeId);


        Vehicle vehicle = qrCode.getVehicle();



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

        return "Fuel limit updated successfully. Remaining quota: " + tracker.getExistingFuel() + " liters.";
    }
}
