package com.fuelmanagement.service;


import com.fuelmanagement.model.entity.mysql.FuelLog;
import com.fuelmanagement.model.entity.mysql.Vehicle;
import com.fuelmanagement.repository.mysql.FuelLogRepository;
import com.fuelmanagement.repository.mysql.VehicleRepository;

import java.util.List;

public class ReportService {

FuelLogRepository fuelLogRepository;
VehicleRepository vehicleRepository;

public Float FuelUsageForEachVehicle(String registrationNumber){

     Vehicle vehicle = vehicleRepository.findByRegistrationNumber(registrationNumber);
     List<FuelLog> allTransactions = fuelLogRepository.findAllByVehicleId(vehicle.getId());

     float totalFuelUsage = 0;
     for (FuelLog fuelLog : allTransactions) {
          totalFuelUsage += fuelLog.getFuelAmount(); // Assuming FuelLog has a field 'fuelAmount'
     }
     return totalFuelUsage;

}

     public Float CostUsageForEachVehicle(String registrationNumber){

          Vehicle vehicle = vehicleRepository.findByRegistrationNumber(registrationNumber);
          List<FuelLog> allTransactions = fuelLogRepository.findAllByVehicleId(vehicle.getId());
          float totalCostUsage = 0;
          float priceForOneLitre = 325.12F;
          for (FuelLog fuelLog : allTransactions) {
               totalCostUsage += fuelLog.getFuelAmount()*priceForOneLitre; // Assuming FuelLog has a field 'fuelAmount'
          }
          return totalCostUsage;

     }

}
