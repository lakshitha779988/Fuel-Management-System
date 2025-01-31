package com.fuelmanagement.controller;

import com.fuelmanagement.model.dto.request.VehicleTypeAddRequest;
import com.fuelmanagement.model.entity.mysql.VehicleType;
import com.fuelmanagement.service.entityService.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-types")
public class VehicleTypeController {

    private  final VehicleTypeService vehicleTypeService;

    @Autowired
    public VehicleTypeController(VehicleTypeService vehicleTypeService) {
        this.vehicleTypeService = vehicleTypeService;
    }

    // Create or update a vehicle type
    @PostMapping
    public ResponseEntity<List<VehicleType>> saveVehicleType(@RequestBody VehicleTypeAddRequest vehicleTypeAddRequest) {
        System.out.println(vehicleTypeAddRequest.getQuota());
        System.out.println(vehicleTypeAddRequest.getName());
       String response =  vehicleTypeService.saveVehicleType(vehicleTypeAddRequest);
        return ResponseEntity.ok(vehicleTypeService.getAllVehicleTypes());
    }

    // Get all vehicle types
    @GetMapping
    public ResponseEntity<List<VehicleType>> getAllVehicleTypes() {
        return ResponseEntity.ok(vehicleTypeService.getAllVehicleTypes());
    }

    // Get vehicle type by name
    @GetMapping("/{typeName}")
    public ResponseEntity<VehicleType> getVehicleTypeByName(@PathVariable String typeName) {
        VehicleType vehicleType = vehicleTypeService.getVehicleTypeByName(typeName);
        if (vehicleType != null) {
            return ResponseEntity.ok(vehicleType);
        }
        return ResponseEntity.notFound().build();
    }
}

