package com.fuelmanagement.controller;

import com.fuelmanagement.model.dto.request.FuelStationLoginRequest;
import com.fuelmanagement.model.dto.request.FuelUserLoginRequest;
import com.fuelmanagement.model.dto.request.RegistrationRequest;
import com.fuelmanagement.model.dto.response.LoginResponse;
import com.fuelmanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;



    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest registrationRequest) {
        // Call the service to handle registration logic
        boolean isRegistered = authService.register(registrationRequest);

        if (isRegistered) {
            // If registration is successful, return a 200 OK response with a success message
            return ResponseEntity.ok("Registration successful");
        } else {
            // If registration fails, return a 400 Bad Request with an error message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed");
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/fuel-user/login")
    public ResponseEntity<LoginResponse> fuelUserLogin( @RequestBody FuelUserLoginRequest request) {
        System.out.println(request.getFirebaseToken() + request.getMobileNumber());
        LoginResponse response = authService.authenticateFuelUser(request.getMobileNumber(), request.getFirebaseToken());
        return ResponseEntity.ok(response);
    }


    @PostMapping("/fuel-station/login")
    public ResponseEntity<LoginResponse> fuelStationLogin( @RequestBody FuelStationLoginRequest request) {
        LoginResponse response = authService.authenticateFuelStation(request.getMobileNumber(), request.getPassword());
        return ResponseEntity.ok(response);
    }
}





