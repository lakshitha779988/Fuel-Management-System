package com.fuelmanagement.service;

import com.fuelmanagement.entity.QRCode;
import com.fuelmanagement.entity.Vehicle;
import com.fuelmanagement.repository.QRCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QRCodeService {
    @Autowired
    private QRCodeRepository qrCodeRepository;

    public QRCode generateQRCodeForVehicle(Vehicle vehicle) {
        QRCode qrCode = new QRCode();
        qrCode.setVehicle(vehicle);
        qrCode.setQrData("ENCODED_DATA_FOR_" + vehicle.getRegistrationNumber());
        qrCode.setCreatedDate(LocalDateTime.now());
        return qrCodeRepository.save(qrCode);
    }
}
