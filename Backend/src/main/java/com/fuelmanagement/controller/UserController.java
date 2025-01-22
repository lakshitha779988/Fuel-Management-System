package com.fuelmanagement.controller;

import com.fuelmanagement.model.dto.response.UserDetailsResponse;
import com.fuelmanagement.service.JwtService;
import com.fuelmanagement.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

//    @GetMapping("/check-mobile-existence")
//    public ResponseEntity<Map<String, Boolean>> checkMobileExistence(@RequestParam String mobileNumber) {
//        boolean exists = userService.isMobileNumberExist(mobileNumber);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("exists", exists);
//        return ResponseEntity.ok(response);
//    }

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

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok((User) userService.createUser((com.fuelmanagement.model.entity.User) user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    // Update user details
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUserDetails) {
        try {
            return ResponseEntity.ok((User) userService.updateUser(userId, (com.fuelmanagement.model.entity.User) updatedUserDetails));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}