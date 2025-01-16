package com.fuelmanagement.service;

import com.fuelmanagement.model.dto.response.UserPrinciple;
import com.fuelmanagement.model.entity.User;
import com.fuelmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {

        User user = userRepository.findByMobileNumber(mobileNumber);

        if(user == null){
            System.out.println("User not Found");
            throw new UsernameNotFoundException("User Not Found");
        }

        return new UserPrinciple(user) ;
    }
}
