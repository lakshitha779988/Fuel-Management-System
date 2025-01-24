package com.fuelmanagement.repository.mysql;

import com.fuelmanagement.model.entity.mysql.FuelStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuelStationRepository extends JpaRepository<FuelStation,Long> {
    Optional<FuelStation> findByName(String username);
}
