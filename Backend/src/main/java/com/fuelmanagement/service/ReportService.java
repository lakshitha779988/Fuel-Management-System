package com.fuelmanagement.service;


import com.fuelmanagement.model.entity.mysql.FuelLog;
import com.fuelmanagement.repository.mysql.FuelLogRepository;

import java.util.List;

public class ReportService {

FuelLogRepository fuelLogRepository;

public Float FuelUsageForEachVehicle(String registrationNumber){

     List<FuelLog> allTransactions = fuelLogRepository.findByVehicle_VehicleRegistrationNumber(registrationNumber);

     float totalFuelUsage = 0;
     for (FuelLog fuelLog : allTransactions) {
          totalFuelUsage += fuelLog.getFuelAmount(); // Assuming FuelLog has a field 'fuelAmount'
     }
     return totalFuelUsage;

}


}
