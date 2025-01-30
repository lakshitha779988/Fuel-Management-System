package com.fuelmanagement.controller;

import com.fuelmanagement.model.dto.request.VehicleDTO;
import com.fuelmanagement.model.dto.request.VehicleDetailsRequest;
import com.fuelmanagement.model.entity.mysql.Vehicle;
import com.fuelmanagement.service.entityService.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/vehicles")
    public class VehicleController {

        private final VehicleService vehicleService;

    @Autowired
        public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    //check vehicle detail with mock database
        @PostMapping("/check-vehicle-details")
        public ResponseEntity<String > checkVehicleDetail(@RequestBody VehicleDetailsRequest vehicleDetail){
              return vehicleService.checkVehicleDetail(vehicleDetail);
        }

        @GetMapping("/{id}")
        public ResponseEntity<VehicleDTO> getVehicleDetails(@PathVariable Long id) {
            Vehicle vehicle = vehicleService.getVehicleById(id);
            return ResponseEntity.ok(new VehicleDTO(vehicle));
        }


}


