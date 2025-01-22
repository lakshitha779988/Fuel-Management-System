package com.fuelmanagement.service;

import com.fuelmanagement.model.dto.response.UserDetailsResponse;
import com.fuelmanagement.model.entity.User;
import com.fuelmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public boolean isMobileNumberExist(String mobileNumber) {
        return userRepository.existsByMobileNumber(mobileNumber);
    }
        public UserDetailsResponse getUserDetails(String mobilenumber) {

            User user = userRepository.findByMobileNumber(mobilenumber).get();
            String firstName = user.getFirstName(); // Get first name from database
            String mobileNumber = user.getMobileNumber();// Get mobile number from database

            // Return only the relevant data as a DTO
            return new UserDetailsResponse(firstName, mobileNumber);
        }

    }


