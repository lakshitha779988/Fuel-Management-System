package com.fuelmanagement.controller;

import com.fuelmanagement.model.entity.mysql.FuelType;
import com.fuelmanagement.service.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public class FuelTypeController {
    @RestController
    @RequestMapping("/api/fuel-types")
    public class fuelTypeController {

        @Autowired
        private FuelTypeService fuelTypeService;

        @GetMapping
        public FuelType getAllFuelTypes() {
            return fuelTypeService. getAllFuelTypes();
        }

        @GetMapping("/{id}")
        public Optional<FuelType> getFuelTypeById(@PathVariable Long id) {
            return fuelTypeService.getFuelTypeById(id);
        }

//        @PostMapping
//        public FuelType createFuelType(@RequestBody FuelType fuelType) {
//            return fuelTypeService.createFuelType(fuelType);
//        }

        @PutMapping("/{id}")
        public FuelType updateFuelType(@PathVariable Long id, @RequestBody FuelType fuelTypeDetails) {
            return fuelTypeService.updateFuelType(id, fuelTypeDetails);
        }

//        @DeleteMapping("/{id}")
//        public void deleteFuelType(@PathVariable Long id) {
//            fuelTypeService.deleteFuelType(id);
//        }
   }

}
