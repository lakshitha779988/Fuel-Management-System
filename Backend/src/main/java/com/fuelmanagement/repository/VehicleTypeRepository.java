package com.fuelmanagement.repository;

import com.fuelmanagement.model.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType,Long> {
    VehicleType findByVehicleTypeName(String vehicleTypeName);
}
