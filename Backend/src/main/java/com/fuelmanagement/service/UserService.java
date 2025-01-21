package com.fuelmanagement.service;

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
}
