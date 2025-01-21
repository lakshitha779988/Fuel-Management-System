package com.fuelmanagement.controller;

import com.fuelmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

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


}
