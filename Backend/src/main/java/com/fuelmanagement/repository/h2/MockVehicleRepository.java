package com.fuelmanagement.repository.h2;

import com.fuelmanagement.model.entity.h2.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MockVehicleRepository extends JpaRepository<Vehicle,Long> {
}
