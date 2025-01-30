package com.fuelmanagement.service.scheduledService;

import com.fuelmanagement.repository.mysql.FuelQuotaTrackerRepository;
import com.fuelmanagement.repository.mysql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuelResetService {

    @Autowired
    private final FuelQuotaTrackerRepository fuelQuotaTrackerRepository;

    public FuelResetService( FuelQuotaTrackerRepository fuelQuotaTrackerRepository) {
        this.fuelQuotaTrackerRepository = fuelQuotaTrackerRepository;

    }

    @Scheduled(cron = "0 0 0 * * MON") // Runs every Monday at 00:00
    @Transactional
    public void resetFuelForAllUsers() {
        int updatedRows = fuelQuotaTrackerRepository.resetFuelQuota();
        System.out.println("Fuel reset for " + updatedRows + " users.");
    }
}
