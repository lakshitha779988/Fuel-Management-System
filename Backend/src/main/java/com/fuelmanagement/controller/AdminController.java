package com.fuelmanagement.controller;


import com.fuelmanagement.model.dto.request.AdminLoginRequest;
import com.fuelmanagement.model.dto.response.FuelStationResponse;
import com.fuelmanagement.service.entityService.AdminService;
import com.fuelmanagement.service.entityService.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminController {


    private  final AdminService  adminService;
    private final FuelStationService fuelStationService;

    @Autowired
    public AdminController(AdminService adminService, FuelStationService fuelStationService) {
        this.adminService = adminService;
        this.fuelStationService = fuelStationService;
    }


    //Admin login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestBody AdminLoginRequest adminLoginRequest){
        System.out.println(adminLoginRequest.getPassword());
        System.out.println(adminLoginRequest.getUserName());

       String token =  adminService.login(adminLoginRequest);
       return ResponseEntity.ok(token);

    }


    //fetch All fuelStation return list of FuelStationResponse
    @GetMapping
    public ResponseEntity<List<FuelStationResponse>> getAllFuelStations() {
        List<FuelStationResponse> fuelStations = fuelStationService.getAllFuelStations();
        return ResponseEntity.ok(fuelStations);
    }


}
