package com.fuelmanagement.service;


import com.fuelmanagement.model.entity.mysql.FuelQuotaTracker;
import com.fuelmanagement.model.entity.mysql.QrCode;
import com.fuelmanagement.model.entity.mysql.User;
import com.fuelmanagement.model.entity.mysql.Vehicle;
import com.fuelmanagement.repository.mysql.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    QrCodeRepository qrCodeRepository;
    @Autowired
    FuelQuotaTrackerRepository fuelQuotaTrackerRepository;

    @Autowired
    FuelLogRepository fuelLogRepository;

    @Transactional
    public String deleteAccount(String mobileNumber) {

        if(!userRepository.existsByMobileNumber(mobileNumber)) {
            throw new IllegalArgumentException("Error in getting Account detail");
        }

        //fetching data about related user
        User user = userRepository.findByMobileNumber(mobileNumber).get();

        if(!vehicleRepository.existsById(user.getVehicle().getId())) {
            throw new IllegalArgumentException("Error in getting Account detail");
        }
        Vehicle vehicle = user.getVehicle();

        if(!fuelQuotaTrackerRepository.existsById(vehicle.getFuelQuotaTracker().getId())){
            throw new IllegalArgumentException("Error in getting Account detail");
        }
        FuelQuotaTracker fuelQuotaTracker = vehicle.getFuelQuotaTracker();

        if(!qrCodeRepository.existsByVehicleId(vehicle.getId())){
            throw new IllegalArgumentException("Error in getting Account detail");
        }
        QrCode qrCode = qrCodeRepository.findByVehicleId(vehicle.getId()).get();

        fuelLogRepository.deleteAllByVehicleId(vehicle.getId());
        //deleting records of the user

        userRepository.deleteById(user.getId());
        qrCodeRepository.deleteById(qrCode.getId());
        vehicleRepository.deleteById(vehicle.getId());
        fuelQuotaTrackerRepository.deleteById(fuelQuotaTracker.getId());

        return "successfully deleted";



    }
}
