package com.fuelmanagement.service.entityService;

import com.fuelmanagement.model.dto.response.UserDetailsResponse;
import com.fuelmanagement.model.entity.mysql.User;
import com.fuelmanagement.model.entity.mysql.Vehicle;
import com.fuelmanagement.repository.mysql.UserRepository;
import com.fuelmanagement.repository.mysql.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;


    @Autowired
    public UserService(UserRepository userRepository, VehicleRepository vehicleRepository) {
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public boolean isMobileNumberExist(String mobileNumber) {
        return userRepository.existsByMobileNumber(mobileNumber);
    }

    public UserDetailsResponse getUserDetails(String mobilenumber) {

        User user = userRepository.findByMobileNumber(mobilenumber).get();
        String firstName = user.getFirstName();
        String mobileNumber = user.getMobileNumber();


        return new UserDetailsResponse(firstName, mobileNumber);
    }


    // Update user details
    public User updateUser(Long userId, User updatedUserDetails) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));


        existingUser.setFirstName(updatedUserDetails.getFirstName());
        existingUser.setLastName(updatedUserDetails.getLastName());
        existingUser.setMobileNumber(updatedUserDetails.getMobileNumber());
        existingUser.setActive(updatedUserDetails.isActive());
        existingUser.setRole(updatedUserDetails.getRole());

        return userRepository.save(existingUser);

    }
  
  public Long findVehicleIdByMobileNumber(String mobileNumber){
        User user = userRepository.findByMobileNumber(mobileNumber).get();
        return user.getVehicle().getId();
        }

    // Add a vehicle to a user
    public Vehicle addVehicleToUser(Long userId, Vehicle vehicle) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        user.setVehicle(vehicle);
        return vehicleRepository.save(vehicle);
    }
}

