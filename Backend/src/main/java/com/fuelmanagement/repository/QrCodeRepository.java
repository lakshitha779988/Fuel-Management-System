package com.fuelmanagement.repository;

import com.fuelmanagement.model.entity.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QrCodeRepository extends JpaRepository<QrCode,Long> {
    QrCode findByQrCode(String qrString);

    boolean existsByQrCode(String qrString);
}
