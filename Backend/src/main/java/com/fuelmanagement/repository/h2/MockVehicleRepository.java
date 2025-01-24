package com.fuelmanagement.repository.h2;

import com.fuelmanagement.model.entity.h2.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MockVehicleRepository extends JpaRepository<Vehicle,Long> {
    Boolean existsByChasisNumberAndRegistrationNumber(String chassisNumber, String registrationNumber);
}
