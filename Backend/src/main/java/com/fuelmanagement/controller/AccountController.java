package com.fuelmanagement.controller;


import com.fuelmanagement.service.AccountService;
import com.fuelmanagement.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {


    private final AccountService accountService;

    private final JwtService jwtService;

    @Autowired
    public AccountController(AccountService accountService, JwtService jwtService) {
        this.accountService = accountService;
        this.jwtService = jwtService;
    }


    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/delete")
    public ResponseEntity<String> deleteAccount(@RequestParam String token){

        String mobileNumber = jwtService.extractIdentifier(token);
        String response = accountService.deleteAccount(mobileNumber);
        return ResponseEntity.ok(response);

    }

}
