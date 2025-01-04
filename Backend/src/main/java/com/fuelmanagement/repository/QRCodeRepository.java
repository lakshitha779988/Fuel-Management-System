package com.fuelmanagement.repository;

import com.fuelmanagement.entity.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
    <T> ScopedValue<T> findByQrData(String qrData);
}
