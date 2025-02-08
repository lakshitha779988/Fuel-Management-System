package com.fuelmanagement.service.loginService;

import org.springframework.stereotype.Component;

@Component
public class UserLoginFactory {

    private final AdminLoginService adminLoginService;
    private final FuelUserLoginService fuelUserLoginService;
    private final FuelStationLoginService fuelStationLoginService;


    public UserLoginFactory(AdminLoginService adminLoginService,
                            FuelUserLoginService fuelUserLoginService,
                            FuelStationLoginService fuelStationLoginService) {
        this.adminLoginService = adminLoginService;
        this.fuelUserLoginService = fuelUserLoginService;
        this.fuelStationLoginService = fuelStationLoginService;
    }

    public LoginService<?> getLoginService(String userType) {
        switch (userType.toLowerCase()) {
            case "admin":
                return adminLoginService;
            case "citizen":
                return fuelUserLoginService;
            case "fuel_station":
                return fuelStationLoginService;
            default:
                throw new IllegalArgumentException("Invalid user type!");
        }
    }
}
