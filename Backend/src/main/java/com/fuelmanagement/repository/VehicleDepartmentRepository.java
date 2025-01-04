package com.fuelmanagement.repository;

import com.fuelmanagement.entity.VehicleDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleDepartmentRepository extends JpaRepository<VehicleDepartment, Long> {
    Optional<VehicleDepartment> findByRegistrationNumber(String registrationNumber);
}