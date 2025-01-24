package com.fuelmanagement.repository.mysql;

import com.fuelmanagement.model.entity.mysql.FuelQuotaTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelQuotaTrackerRepository extends JpaRepository<FuelQuotaTracker,Long> {
}
