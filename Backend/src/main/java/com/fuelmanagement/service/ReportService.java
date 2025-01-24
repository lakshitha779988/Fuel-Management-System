package com.fuelmanagement.service;



import com.fuelmanagement.model.entity.mysql.*;
import com.fuelmanagement.repository.mysql.FuelLogRepository;
import com.fuelmanagement.repository.mysql.FuelStationRepository;
import com.fuelmanagement.repository.mysql.UserRepository;
import com.fuelmanagement.repository.mysql.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
  
     @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private FuelLogRepository fuelLogRepository;

//FuelUsageForEachVehicle
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

     //fuelUsageForUser
  
public Float fuelUsageForUser(Long userId) {
    // Fetch the user by ID
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

    // Get all vehicles owned by the user

  List<FuelLog> allTransactions = fuelLogRepository.findAllByUserId(user.getId());


    // Calculate total fuel usage for the user
  float totalCostUsage = 0;
  float priceForOneLitre = 325.12F;
  for (FuelLog fuelLog : allTransactions) {
      totalCostUsage += fuelLog.getFuelAmount()*priceForOneLitre; // Assuming FuelLog has a field 'fuelAmount'
  }
  return totalCostUsage;
}

//fuel distribution usage for a fuel station
@Autowired
private FuelStationRepository fuelStationRepository;

    // Calculate total fuel distributed at a fuel station
    public Float fuelDistributionUsageForStation(Long stationId) {
        // Fetch the fuel station by ID
        FuelStation station = fuelStationRepository.findById(stationId)
                .orElseThrow(() -> new IllegalArgumentException("Fuel station not found with ID: " + stationId));

        // Fetch all fuel logs for the station
        List<FuelLog> fuelLogs = fuelLogRepository.findByStationId(stationId);

        // Aggregate the total fuel distributed at this station
        float totalFuelDistributed = 0.0f;
        for (FuelLog log : fuelLogs) {
            totalFuelDistributed += log.getFuelPumped();
        }

        return totalFuelDistributed;
    }
}

