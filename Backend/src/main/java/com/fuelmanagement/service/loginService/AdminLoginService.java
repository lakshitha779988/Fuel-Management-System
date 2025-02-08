package com.fuelmanagement.service.loginService;

import com.fuelmanagement.model.dto.request.AdminLoginRequest;
import com.fuelmanagement.model.dto.response.LoginResponse;
import com.fuelmanagement.model.entity.mysql.Admin;
import com.fuelmanagement.repository.mysql.AdminRepository;
import com.fuelmanagement.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdminLoginService implements LoginService<AdminLoginRequest> {


    private final AdminRepository adminRepository;
    private final JwtService jwtService;

    @Autowired
    public AdminLoginService(AdminRepository adminRepository, JwtService jwtService) {
        this.adminRepository = adminRepository;
        this.jwtService = jwtService;
    }


    @Override
    public LoginResponse login(AdminLoginRequest adminLoginRequest) {
        Admin admin = adminRepository.findByUserName(adminLoginRequest.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        if (!Objects.equals(adminLoginRequest.getPassword(), admin.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        String token = jwtService.generateToken(admin.getUserName(), "FUEL_STATION", "admin");

       LoginResponse loginResponse = new LoginResponse(token);
       return loginResponse;
    }
}
