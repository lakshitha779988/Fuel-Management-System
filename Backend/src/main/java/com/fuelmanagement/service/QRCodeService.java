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

        // Encode QR data (use vehicle's registration number or unique string)
        String encodedData = "VEHICLE:" + vehicle.getVehicleRegistrationNumber() + "|QUOTA:" + Vehicle.getCurrentQuota();
        qrCode.setQrData(encodedData);
        qrCode.setQrCreatedDate(LocalDateTime.now());
        return qrCodeRepository.save(qrCode);
    }

    public QRCode getQRCodeDetails(String qrData) {
        return (QRCode) qrCodeRepository.findByQrData(qrData)
                .orElseThrow(() -> new RuntimeException("QR Code not found"));
    }
}