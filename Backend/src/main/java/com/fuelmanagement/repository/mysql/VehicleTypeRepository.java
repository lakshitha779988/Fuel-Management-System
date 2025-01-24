package com.fuelmanagement.repository.mysql;

import com.fuelmanagement.model.entity.mysql.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType,Long> {
    VehicleType findByVehicleTypeName(String vehicleTypeName);
}
