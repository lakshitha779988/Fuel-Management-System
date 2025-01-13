package com.fuelmanagement.repository;

import com.fuelmanagement.model.entity.FuelLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelLogRepository extends JpaRepository<FuelLog,Long> {
}
