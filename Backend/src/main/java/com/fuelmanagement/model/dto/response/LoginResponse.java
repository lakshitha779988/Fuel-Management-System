package com.fuelmanagement.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;           // JWT token
    private String userType;        // FUEL_USER or FUEL_STATION
    private String username;        // null for Fuel Users, populated for Fuel Stations
    private String mobileNumber;    // populated for Fuel Users, null for Fuel Stations
    private String roles;     // User roles for access control
}
