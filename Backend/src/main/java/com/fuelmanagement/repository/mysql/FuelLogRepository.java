package com.fuelmanagement.repository.mysql;

import com.fuelmanagement.model.entity.mysql.FuelLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
C
import java.util.List;

@Repository
public interface FuelLogRepository extends JpaRepository<FuelLog,Long> {

    //Get all transaction to relevent vehicle accordinng to vehicleRegistrationNumber
    List<FuelLog> findByVehicle_VehicleRegistrationNumber(String vehicleRegistrationNumber);

}
