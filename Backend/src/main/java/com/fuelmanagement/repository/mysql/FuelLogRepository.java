package com.fuelmanagement.repository.mysql;

import com.fuelmanagement.model.entity.mysql.FuelLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelLogRepository extends JpaRepository<FuelLog,Long> {
}
