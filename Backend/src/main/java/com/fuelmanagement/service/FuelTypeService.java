package com.fuelmanagement.service;

import com.fuelmanagement.model.entity.mysql.FuelType;
import com.fuelmanagement.repository.mysql.FuelTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuelTypeService {
    @Autowired
    private FuelTypeRepository.fuelTypeRepository fuelTypeRepository;

    public FuelType getAllFuelTypes() {
        return (FuelType) fuelTypeRepository.findAll();
    }

    public Optional<FuelType> getFuelTypeById(Long id) {
        return fuelTypeRepository.findById(FuelType.Id);

    }

//    public FuelType createFuelType(FuelType fuelType) {
//    }

//    public FuelType updateFuelType(Long id, FuelType fuelTypeDetails) {
//        FuelType fuelType = new FuelType();
//        return fuelTypeRepository.save(fuelType);
//    }
public Optional<FuelType> getFuelTypeByName(String fuelTypeName) {
    return fuelTypeRepository.findByName(fuelTypeName);
}

    // @Service
//    public class fuelTypeService {
//
//        @Autowired
//        private FuelTypeRepository.fuelTypeRepository fuelTypeRepository;
//
//        // Create or update a fuel type
//        public FuelType saveFuelType(FuelType fuelType) {
//            return fuelTypeRepository.save(fuelType);
//        }
//
//        // Fetch all fuel types
//        public List<FuelType> getAllFuelTypes() {
//            return fuelTypeRepository.findAll();
//        }
//
//        // Fetch a fuel type by id
//        public Optional<FuelType> getFuelTypeById(String VehicleTypeId) {
//            return fuelTypeRepository.findById(FuelType.Id);
//        }
//
//
//    }

}
