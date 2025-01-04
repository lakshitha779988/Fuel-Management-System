package com.fuelmanagement.repository;

import com.fuelmanagement.entity.VehicleQuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleQuotaRepository extends JpaRepository<VehicleQuota, Long> {
    Optional<VehicleQuota> findByVehicleType(String vehicleType);
}
