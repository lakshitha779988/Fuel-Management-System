package com.fuelmanagement.service.entityService;

import com.fuelmanagement.model.dto.response.FuelStationResponse;
import com.fuelmanagement.repository.mysql.FuelStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuelStationService {


    private final FuelStationRepository fuelStationRepository;


    @Autowired
    public FuelStationService(FuelStationRepository fuelStationRepository) {
        this.fuelStationRepository = fuelStationRepository;

    }


    public Long getFuelStationIdByMobileNumber(String mobileNumber) {
        return fuelStationRepository.findByMobileNumber(mobileNumber).get().getId();
    }


    public List<FuelStationResponse> getAllFuelStations() {
        System.out.println("come to function");
        return fuelStationRepository.findAll()
                .stream()
                .map(fuelStation -> new FuelStationResponse(
                        fuelStation.getId(),
                        fuelStation.getName(),
                        fuelStation.getEmail(),
                        fuelStation.getMobileNumber(),
                        fuelStation.getStatus(),
                        fuelStation.getStock()))
                .collect(Collectors.toList());
    }
}
