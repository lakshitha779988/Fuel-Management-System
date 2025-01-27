package com.fuelmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fuelmanagement.service.FuelQuotaService;

@RestController
@RequestMapping("api/fuel-quota")
public class FuelQuotaController {

    @Autowired
    private FuelQuotaService fuelQuotaService;

    @PutMapping("/update-limit")
    public ResponseEntity<String> updateFuelLimit(@RequestParam String qrString, @RequestParam float usage) {

        System.out.println(qrString);
        try {

            String responseMessage = fuelQuotaService.updateFuelLimit(qrString, usage);
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
