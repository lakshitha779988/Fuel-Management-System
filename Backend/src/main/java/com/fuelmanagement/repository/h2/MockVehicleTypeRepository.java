package com.fuelmanagement.repository.h2;

import com.fuelmanagement.model.entity.h2.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MockVehicleTypeRepository extends JpaRepository<VehicleType, Integer> {
}
