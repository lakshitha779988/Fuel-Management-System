package com.fuelmanagement.service.registerService;


import org.springframework.stereotype.Component;

@Component
public class UserRegistrationFactory {

    private final FuelStationRegisterService fuelStationRegisterService;
    private final FuelUserRegisterService fuelUserRegisterService;


    public UserRegistrationFactory(FuelStationRegisterService fuelStationRegisterService, FuelUserRegisterService fuelUserRegisterService) {
        this.fuelStationRegisterService = fuelStationRegisterService;
        this.fuelUserRegisterService = fuelUserRegisterService;
    }


    public RegisterService<?> getRegisterService(String userType) {
        switch (userType.toLowerCase()) {
            case "citizen":
                return fuelUserRegisterService;
            case "fuel_station":
                return fuelStationRegisterService;
            default:
                throw new IllegalArgumentException("Invalid user type!");
        }
    }
}
