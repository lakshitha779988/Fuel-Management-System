package com.fuelmanagement.service;

import com.fuelmanagement.entity.FuelStation;
import com.fuelmanagement.repository.FuelStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelStationService {
    @Autowired
    private FuelStationRepository fuelStationRepository;

    public FuelStation registerFuelStation(FuelStation fuelStation) {
        return fuelStationRepository.save(fuelStation);
    }

    public List<FuelStation> getAllFuelStations() {
        return fuelStationRepository.findAll();
    }
}
