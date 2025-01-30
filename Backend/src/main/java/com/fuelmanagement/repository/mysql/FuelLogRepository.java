package com.fuelmanagement.repository.mysql;

import com.fuelmanagement.model.entity.mysql.FuelLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelLogRepository extends JpaRepository<FuelLog,Long> {


    List<FuelLog> findAllByVehicleId(Long id);

    List<FuelLog> findAllByUserId(Long id);

    void deleteAllByVehicleId(Long id);
}
