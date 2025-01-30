package com.fuelmanagement.repository.mysql;

import com.fuelmanagement.model.entity.mysql.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface QrCodeRepository extends JpaRepository<QrCode,Long> {
    // Methods from joji branch
    Optional<QrCode> findByVehicleId(Long vehicleId);
    void deleteByVehicleId(Long vehicleId);
    
    // Methods from main branch
    Optional<QrCode> findByQrCode(String qrString);
    boolean existsByQrCode(String qrString);

    boolean existsByVehicleId(Long id);
}