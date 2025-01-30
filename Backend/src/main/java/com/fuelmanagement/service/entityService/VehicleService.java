package com.fuelmanagement.service.entityService;

import com.fuelmanagement.model.dto.request.VehicleDetailsRequest;
import com.fuelmanagement.model.entity.mysql.Vehicle;
import com.fuelmanagement.repository.h2.MockVehicleRepository;
import com.fuelmanagement.repository.mysql.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
    public class VehicleService {
        @Autowired
        private VehicleRepository vehicleRepository;

        @Autowired
        private MockVehicleRepository mockVehicleRepository;

        public Vehicle registerVehicle(Vehicle vehicle) {
            // Add validation logic (e.g., mock Motor Traffic DB validation)
            return vehicleRepository.save(vehicle);
        }


        
        public Vehicle getVehicleById(Long vehicleId) {
            // Fetch the vehicle details using its ID
            return vehicleRepository.findById(vehicleId)
                    .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with ID: " + vehicleId));
        }

        public ResponseEntity<String> checkVehicleDetail(VehicleDetailsRequest vehicleDetails){
            Boolean isValid = mockVehicleRepository.existsByChasisNumberAndRegistrationNumber(
                    vehicleDetails.getChassisNumber(),
                    vehicleDetails.getRegistrationNumber()
            );

            if (!isValid) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle detail are incorrect");
            } else {
                Boolean isExsit = vehicleRepository.existsByRegistrationNumber(vehicleDetails.getRegistrationNumber());

                if (!isExsit){
                    return ResponseEntity.ok("Vehicle details are valid.");
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle with this registration number already exists.");
                }

            }

        }






    // Add or update a Vehicle
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Delete a Vehicle by ID
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
    }

