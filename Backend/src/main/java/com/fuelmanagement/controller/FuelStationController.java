package com.fuelmanagement.controller;

import com.fuelmanagement.model.dto.request.FuelStationLoginRequest;
import com.fuelmanagement.model.dto.request.FuelStationRequest;
import com.fuelmanagement.model.dto.response.LoginResponse;
import com.fuelmanagement.service.entityService.FuelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/fuel-stations")
public class FuelStationController {


    private final FuelStationService fuelStationService;


    @Autowired
    public FuelStationController(FuelStationService fuelStationService) {
        this.fuelStationService = fuelStationService;

    }


    //make end point for reports



}
