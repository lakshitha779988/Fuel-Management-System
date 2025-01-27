package com.fuelmanagement.controller;

import com.fuelmanagement.model.dto.request.FuelStationLoginRequest;
import com.fuelmanagement.model.dto.request.FuelStationRequest;
import com.fuelmanagement.model.dto.request.FuelUserLoginRequest;
import com.fuelmanagement.model.dto.response.LoginResponse;
import com.fuelmanagement.service.AuthService;
import com.fuelmanagement.service.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/fuel-stations")
public class FuelStationController {

    @Autowired
    private FuelStationService fuelStationService;

    @Autowired
    private  AuthService authService;

    @CrossOrigin(origins = "http://localhost:8082")
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


    @CrossOrigin(origins = "http://localhost:8082")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> fuelUserLogin(@RequestBody FuelStationLoginRequest request) {
        System.out.println("Mobile Number: " + request.getMobileNumber());
        System.out.println("Password: " + request.getPassword());

        // Authenticate the fuel user using the provided mobile number and password
        LoginResponse response = authService.authenticateFuelStation(request.getMobileNumber().toString()   , request.getPassword());
    System.out.println(response);
        // Return the login response with the JWT token and other user details
        return ResponseEntity.ok(response);
    }
}
