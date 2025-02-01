package com.fuelmanagement.service.loginService;

import com.fuelmanagement.model.dto.request.FuelStationLoginRequest;
import com.fuelmanagement.model.dto.response.LoginResponse;
import com.fuelmanagement.model.entity.mysql.FuelStation;
import com.fuelmanagement.repository.mysql.FuelStationRepository;
import com.fuelmanagement.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FuelStationLoginService implements LoginService<FuelStationLoginRequest> {

    private final FuelStationRepository fuelStationRepository;
    private final JwtService jwtService;


    @Autowired
    public FuelStationLoginService(FuelStationRepository fuelStationRepository, JwtService jwtService) {
        this.fuelStationRepository = fuelStationRepository;
        this.jwtService = jwtService;
    }


    @Override
    public LoginResponse login(FuelStationLoginRequest fuelStationLoginRequest) {
        FuelStation station = fuelStationRepository.findByMobileNumber(fuelStationLoginRequest.getMobileNumber())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        System.out.println(station);
        // Step 2: Verify the password
        if (!Objects.equals(fuelStationLoginRequest.getPassword(), station.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        if (!Objects.equals(station.getStatus(), "Active")) {
            throw new BadCredentialsException("Account is not active yet");
        }


        System.out.println(station.getEmail());
        // Step 3: Generate JWT token
        String token = jwtService.generateToken(station.getMobileNumber(), "ADMIN", "fuel_station");
        System.out.println(token);
        // Step 4: Return response
        return new LoginResponse(token);
    }

}
