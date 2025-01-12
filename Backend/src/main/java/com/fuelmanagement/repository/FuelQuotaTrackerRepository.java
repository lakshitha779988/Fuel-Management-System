package com.fuelmanagement.repository;

import com.fuelmanagement.model.entity.FuelQuotaTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelQuotaTrackerRepository extends JpaRepository<FuelQuotaTracker,Long> {
}
