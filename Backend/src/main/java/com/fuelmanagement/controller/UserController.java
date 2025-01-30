package com.fuelmanagement.controller;

import com.fuelmanagement.model.dto.response.UserDetailsResponse;
import com.fuelmanagement.model.entity.mysql.Vehicle;
import com.fuelmanagement.service.JwtService;
import com.fuelmanagement.service.entityService.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {


    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }



    //check if that mobile number available in our system
    @CrossOrigin(origins = "http://localhost:5173")  // Allow requests from the frontend (if needed)
    @GetMapping("/check-mobile-existence")
    public ResponseEntity<Map<String, Boolean>> lk(@RequestParam String mobileNumber) {
        Map<String, Boolean> response = new HashMap<>();
        boolean exists = userService.isMobileNumberExist(mobileNumber);
        response.put("exists", exists);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/details")
    public ResponseEntity<UserDetailsResponse> getUserDetails(@RequestParam String token) {
        // Here, you would parse the JWT token to extract user info (e.g., userId)

       String mobileNumber =  jwtService.extractIdentifier(token);

        // Fetch user details using the service
        UserDetailsResponse userDetails = userService.getUserDetails(mobileNumber);

        // Return user details in the response
        return ResponseEntity.ok(userDetails);
    }

}