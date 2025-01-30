package com.fuelmanagement.controller;


import com.fuelmanagement.model.dto.request.AdminLoginRequest;
import com.fuelmanagement.model.dto.response.FuelStationResponse;
import com.fuelmanagement.service.entityService.AdminService;
import com.fuelmanagement.service.entityService.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminContoller {

    @Autowired
    AdminService adminService;

    @Autowired
    FuelStationService fuelStationService;

    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(AdminLoginRequest adminLoginRequest){

       String token =  adminService.login(adminLoginRequest);
       return ResponseEntity.ok(token);

    }


    @GetMapping
    public ResponseEntity<List<FuelStationResponse>> getAllFuelStations() {
        List<FuelStationResponse> fuelStations = fuelStationService.getAllFuelStations();
        return ResponseEntity.ok(fuelStations);
    }


}
