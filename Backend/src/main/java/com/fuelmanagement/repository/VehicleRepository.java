package com.fuelmanagement.repository;


import com.fuelmanagement.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    Vehicle findByRegistrationNumber(String registrationNumber);

    boolean existsByRegistrationNumber(String vehicleNumber);

    Vehicle findByQrCodeId(Long qrCodeId);

    boolean existsByQrCodeId(Long qrCodeId);
}
