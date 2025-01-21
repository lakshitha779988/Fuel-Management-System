package com.fuelmanagement.repository;

import com.fuelmanagement.model.entity.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface QrCodeRepository extends JpaRepository<QrCode,Long> {
    Optional<QrCode> findByVehicleId(Long vehicleId);
    void deleteByVehicleId(Long vehicleId);
}
