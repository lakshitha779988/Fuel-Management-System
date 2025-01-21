package com.fuelmanagement.controller;

import com.fuelmanagement.model.dto.request.FuelStationRequest;
import com.fuelmanagement.service.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fuel-stations")
public class FuelStationController {

    @Autowired
    private FuelStationService fuelStationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerFuelStation(@RequestBody FuelStationRequest fuelStationRequest) {
        try {
            String responseMessage = fuelStationService.registerFuelStation(fuelStationRequest);
            return ResponseEntity.ok(responseMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while registering the fuel station.");
        }
    }
}
