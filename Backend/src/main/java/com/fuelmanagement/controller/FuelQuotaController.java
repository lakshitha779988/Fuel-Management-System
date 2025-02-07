package com.fuelmanagement.controller;

import com.fuelmanagement.service.JwtService;
import com.fuelmanagement.service.entityService.FuelStationService;
import com.fuelmanagement.service.qrCodeScanService.QRCodeScanner;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/fuel-quota")
public class FuelQuotaController {

    private final QRCodeScanner qrCodeScanner;
    private final JwtService jwtService;
    private final FuelStationService fuelStationService;

    @Autowired
    public FuelQuotaController(QRCodeScanner qrCodeScanner, JwtService jwtService, FuelStationService fuelStationService) {
        this.qrCodeScanner = qrCodeScanner;
        this.jwtService = jwtService;
        this.fuelStationService = fuelStationService;
    }

    @PutMapping("/update-limit")
    public ResponseEntity<String> updateFuelLimit(
            @RequestParam String qrString,
            @RequestParam float usage,
            HttpServletRequest request) {

        try {
            String authorizationHeader = request.getHeader("Authorization");

            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.badRequest().body("Authorization header missing or invalid");
            }

            String token = authorizationHeader.substring(7);
            String mobileNumber = jwtService.extractIdentifier(token);
            Long fuelStationId = fuelStationService.getFuelStationIdByMobileNumber(mobileNumber);

            System.out.println("Fuel Station ID: " + fuelStationId);

            qrCodeScanner.scanQRCode(qrString, usage, fuelStationId);
            return ResponseEntity.ok("FuelAmount Update Successfully ");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while updating the fuel limit.");
        }
    }
}
