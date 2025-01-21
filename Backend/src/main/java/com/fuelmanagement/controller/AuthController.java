package com.fuelmanagement.controller;


import com.fuelmanagement.model.dto.request.LoggingRequest;
import com.fuelmanagement.model.dto.request.RegistrationRequest;
import com.fuelmanagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public void register(@RequestBody RegistrationRequest registrationRequest){
        authService.register(registrationRequest);
    }

    @PostMapping("logging")
    public void logging(@RequestBody LoggingRequest loggingRequest){
        authService.login(loggingRequest);
    }

    @GetMapping("register")
    public String  hi(){
        return "hiii";
    }
}
