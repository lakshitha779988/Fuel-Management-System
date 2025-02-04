package com.fuelmanagement.service.entityService;

import com.fuelmanagement.model.dto.response.FuelStationResponse;
import com.fuelmanagement.model.entity.mysql.FuelStation;
import com.fuelmanagement.repository.mysql.FuelStationRepository;
import com.google.api.gax.rpc.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public FuelStation getFuelStationById(Long id){
        Optional<FuelStation> fuelStation = fuelStationRepository.findById(id);
        if(fuelStation.isEmpty()){
            throw new IllegalArgumentException("FuelStation id is not valid");
        }
        return fuelStation.get();
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
