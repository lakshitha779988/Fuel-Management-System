package com.fuelmanagement.repository;


import com.fuelmanagement.model.entity.FuelStation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FuelStationRepository extends JpaRepository<FuelStation,Long> {
}

