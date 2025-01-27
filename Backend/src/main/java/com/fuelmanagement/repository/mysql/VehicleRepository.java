package com.fuelmanagement.repository.mysql;


import com.fuelmanagement.model.entity.mysql.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    Vehicle findByRegistrationNumber(String registrationNumber);

    boolean existsByRegistrationNumber(String vehicleNumber);

    Vehicle findByQrCodeId(Long qrCodeId);

    boolean existsByQrCodeId(Long qrCodeId);



}
