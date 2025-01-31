package com.fuelmanagement.controller;


import com.fuelmanagement.model.dto.request.AdminLoginRequest;
import com.fuelmanagement.model.dto.response.AdminLoginResponse;
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
    public ResponseEntity<AdminLoginResponse> adminLogin(@RequestBody AdminLoginRequest adminLoginRequest){
        System.out.println(adminLoginRequest.getPassword());
        System.out.println(adminLoginRequest.getUserName());

       String token =  adminService.login(adminLoginRequest);
       AdminLoginResponse adminLoginResponse = new AdminLoginResponse(token);
       return ResponseEntity.ok(adminLoginResponse);

    }


    //fetch All fuelStation return list of FuelStationResponse
    @GetMapping("/fuelStation")
    public ResponseEntity<List<FuelStationResponse>> getAllFuelStations() {
        System.out.println("come");
        List<FuelStationResponse> fuelStations = fuelStationService.getAllFuelStations();
        return ResponseEntity.ok(fuelStations);
    }

    @PutMapping("/toggle-status/{id}")
    public ResponseEntity<String> toggleFuelStationStatus(@PathVariable Long id) {
        adminService.changeFuelStationStatus(id);
        return ResponseEntity.ok("Fuel station status toggled successfully.");
    }


}
