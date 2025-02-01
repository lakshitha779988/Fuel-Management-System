package com.fuelmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuelmanagement.model.dto.request.*;
import com.fuelmanagement.service.loginService.UserLoginFactory;
import com.fuelmanagement.model.dto.response.LoginResponse;
import com.fuelmanagement.service.loginService.LoginService;
import com.fuelmanagement.service.registerService.RegisterService;
import com.fuelmanagement.service.registerService.UserRegistrationFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    @Autowired
    private UserLoginFactory userLoginFactory;
    @Autowired
    private UserRegistrationFactory userRegistrationFactory;



    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String userType, @RequestBody Map<String, Object> requestBody) {
        RegisterService<?> registerService = userRegistrationFactory.getRegisterService(userType);
        Object dto = mapToDTO2(userType, requestBody);

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        String response = ((RegisterService<Object>) registerService).register(dto);
        return ResponseEntity.ok(response);
    }



    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam String userType, @RequestBody Map<String, Object> requestBody) {
        LoginService<?> loginService = userLoginFactory.getLoginService(userType);
        Object dto = mapToDTO(userType, requestBody);

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        LoginResponse response = ((LoginService<Object>) loginService).login(dto);
        return ResponseEntity.ok(response);
    }

    private Object mapToDTO(String userType, Map<String, Object> requestBody) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            switch (userType.toLowerCase()) {
                case "admin":
                    return objectMapper.convertValue(requestBody, AdminLoginRequest.class);
                case "citizen":
                    return objectMapper.convertValue(requestBody, FuelUserLoginRequest.class);
                case "fuel_station":
                    return objectMapper.convertValue(requestBody, FuelStationLoginRequest.class);
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }


    private Object mapToDTO2(String userType, Map<String, Object> requestBody) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            switch (userType.toLowerCase()) {
                case "citizen":
                    return objectMapper.convertValue(requestBody, RegistrationRequest.class);
                case "fuel_station":
                    return objectMapper.convertValue(requestBody, FuelStationRequest.class);
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }



}





