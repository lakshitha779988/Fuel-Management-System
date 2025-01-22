package com.fuelmanagement.controller;

import com.fuelmanagement.model.entity.Vehicle;
import com.fuelmanagement.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/vehicles")
    public class VehicleController {
        @Autowired
        private VehicleService vehicleService;

        @PostMapping("/register")
        public ResponseEntity<Vehicle> registerVehicle(@RequestBody Vehicle vehicle) {
            return ResponseEntity.ok(vehicleService.registerVehicle(vehicle));
        }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/test")
    public String greetMassage(){
            return "Wellcome to the Oure System";
        }

    }


