package com.fuelmanagement.security;

import com.fuelmanagement.model.entity.FuelStation;
import com.fuelmanagement.model.entity.User;
import com.fuelmanagement.repository.FuelStationRepository;
import com.fuelmanagement.repository.UserRepository;
import com.fuelmanagement.service.JwtService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final JwtService jwtService;  // Inject JWT service to verify tokens
    private final UserRepository fuelUserRepository; // Repository for FuelUser
    private final FuelStationRepository fuelStationRepository; // Repository for FuelStation

    public CustomAuthenticationProvider(JwtService jwtService,
                                        UserRepository fuelUserRepository,
                                        FuelStationRepository fuelStationRepository) {
        this.jwtService = jwtService;
        this.fuelUserRepository = fuelUserRepository;
        this.fuelStationRepository = fuelStationRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        String token = (String) authentication.getCredentials();

        // Validate token
        if (!jwtService.isTokenValid(token)) {
            throw new RuntimeException("Invalid JWT Token.");
        }

        // Extract username, role, and other details from token
        String username = jwtService.extractIdentifier(token);
        String role = jwtService.extractRole(token);

        // Handle FuelUser (authenticate with mobile number)
        if ("FUEL_USER".equals(role)) {
            // Check FuelUser by mobile number (or other identifier)
             User fuelUser = fuelUserRepository.findByMobileNumber(username)
                    .orElseThrow(() -> new RuntimeException("Fuel User not found"));

            // Validate and create the authentication token
            CustomAuthenticationToken customAuthToken = new CustomAuthenticationToken(
                    fuelUser.getMobileNumber(), token, Collections.singleton(new SimpleGrantedAuthority(role))
            );
            return customAuthToken;
        }

        // Handle FuelStation (authenticate with username and password)
        if ("FUEL_STATION".equals(role)) {
            // Check FuelStation by username
            FuelStation fuelStation = fuelStationRepository.findByName(username)
                    .orElseThrow(() -> new RuntimeException("Fuel Station not found"));

            // Here, you might want to validate the password
            // If password is required, you can add a password check here

            // Create and return the authentication token for FuelStation
            CustomAuthenticationToken customAuthToken = new CustomAuthenticationToken(
                    fuelStation, token, Collections.singleton(new SimpleGrantedAuthority(role))
            );
            return customAuthToken;
        }

        throw new RuntimeException("Invalid Role in Token.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
