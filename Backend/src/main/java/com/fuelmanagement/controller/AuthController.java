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
    public void register(@RequestBody RegistrationRequest registrationRequest){
        authService.register(registrationRequest);
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
        LoginResponse response = authService.authenticateFuelStation(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(response);
    }
}





