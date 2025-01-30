package com.fuelmanagement.repository.mysql;

import com.fuelmanagement.model.entity.mysql.FuelQuotaTracker;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelQuotaTrackerRepository extends JpaRepository<FuelQuotaTracker, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE fuel_quota_tracker SET existing_fuel = weekly_consumption, reset_date = CURDATE()", nativeQuery = true)
    int resetFuelQuota();

}
