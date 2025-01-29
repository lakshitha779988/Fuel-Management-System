package com.fuelmanagement.controller;

import com.fuelmanagement.service.entityService.FuelStationService;
import com.fuelmanagement.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fuelmanagement.service.entityService.FuelQuotaService;

@RestController
@RequestMapping("api/fuel-quota")
public class FuelQuotaController {

    @Autowired
    private FuelQuotaService fuelQuotaService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private FuelStationService fuelStationService;

    @PutMapping("/update-limit")
    public ResponseEntity<String> updateFuelLimit(
            @RequestParam String qrString,
            @RequestParam float usage,
            HttpServletRequest request) {

        try {
            // Extract the Authorization header
            String authorizationHeader = request.getHeader("Authorization");

            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.badRequest().body("Authorization header missing or invalid");
            }

            // Extract the token from the Authorization header
            String token = authorizationHeader.substring(7); // Remove "Bearer " prefix

            // Use the token to get the fuel station's mobile number (example implementation)
            String mobileNumber = jwtService.extractIdentifier(token); // Your JWT parsing logic here
            Long fuelStationId = fuelStationService.getFuelStationIdByMobileNumber(mobileNumber);

            System.out.println("Fuel Station ID: " + fuelStationId);

            // Proceed with updating the fuel limit
            String responseMessage = fuelQuotaService.updateFuelLimit(qrString, usage,fuelStationId);
            return ResponseEntity.ok(responseMessage);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while updating the fuel limit.");
        }
    }
}
