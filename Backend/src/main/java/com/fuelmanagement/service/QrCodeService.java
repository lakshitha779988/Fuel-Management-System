package com.fuelmanagement.service;

import com.fuelmanagement.model.dto.response.QrCodeCheckingResponse;
import com.fuelmanagement.model.entity.mysql.FuelQuotaTracker;
import com.fuelmanagement.model.entity.mysql.QrCode;
import com.fuelmanagement.model.entity.mysql.Vehicle;
import com.fuelmanagement.repository.mysql.FuelQuotaTrackerRepository;
import com.fuelmanagement.repository.mysql.QrCodeRepository;
import com.fuelmanagement.repository.mysql.VehicleRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class QrCodeService {

    @Autowired
    private QrCodeRepository qrCodeRepository;

    @Autowired
    private VehicleRepository vehicleRepository;



    // Generate a unique string for the QR code
    private String generateUniqueQRCodeString() {
        return UUID.randomUUID().toString();
    }

    // Generate a QR code as a byte array without saving it to a file
    public byte[] generateQRCodeImage(String qrCodeString) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeString, BarcodeFormat.QR_CODE, 300, 300);

        // Return the QR code as a byte array
        try (ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            System.out.println(pngOutputStream);
            return pngOutputStream.toByteArray();
        }
    }

    // Create a QR code for a vehicle
    public byte[] createQRCode(Long vehicleId) throws Exception {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);

        if (vehicleOptional.isEmpty()) {
            throw new IllegalArgumentException("Vehicle not found.");
        }

        Vehicle vehicle = vehicleOptional.get();
        String qrCodeString;
        // Check if a QR code already exists
        if (qrCodeRepository.findByVehicleId(vehicleId).isPresent()) {
            QrCode qrCode = qrCodeRepository.findByVehicleId(vehicleId).get();
             qrCodeString = qrCode.getQrCode();
            System.out.println(qrCodeString);
        }else{
            qrCodeString = generateUniqueQRCodeString();
            System.out.println(qrCodeString);
            QrCode qrCode = new QrCode(qrCodeString, vehicle);
            qrCodeRepository.save(qrCode);
        }




        // Generate the QR code and return it as a byte array (without saving to a file)
        return generateQRCodeImage(qrCodeString);
    }

    // Update the QR code for a vehicle
    public byte[] updateQRCode(Long vehicleId) throws Exception {
        Optional<QrCode> qrCodeOptional = qrCodeRepository.findByVehicleId(vehicleId);

        if (qrCodeOptional.isEmpty()) {
            throw new IllegalArgumentException("QR Code not found for this vehicle.");
        }

        QrCode qrCode = qrCodeOptional.get();
        String newQrCodeString = generateUniqueQRCodeString();
        qrCode.setQrCode(newQrCodeString);
        qrCodeRepository.save(qrCode);

        // Generate the updated QR code and return it as a byte array (without saving to a file)
        return generateQRCodeImage(newQrCodeString);
    }

    // Delete a QR code record
    public void deleteQRCode(Long vehicleId) {
        if (!qrCodeRepository.findByVehicleId(vehicleId).isPresent()) {
            throw new IllegalArgumentException("QR Code not found for this vehicle.");
        }

        qrCodeRepository.deleteByVehicleId(vehicleId);
    }

    public QrCodeCheckingResponse checkIsQrValid(String qrCode) {
       QrCode qrCodeObj =  qrCodeRepository.findByQrCode(qrCode).get();
       if(qrCodeObj == null){
           throw new IllegalArgumentException("QR Code is not valid.");
       }
       System.out.println(qrCodeObj);
      Vehicle vehicle= qrCodeObj.getVehicle();

       if(vehicle==null){
           throw new IllegalArgumentException("Not any vehicle available for this QR");
       }

       System.out.println(vehicle.getRegistrationNumber());

        FuelQuotaTracker fuelQuotaTracker = vehicle.getFuelQuotaTracker();

        System.out.println(fuelQuotaTracker.getExistingFuel());

        QrCodeCheckingResponse qrCodeCheckingResponse = new QrCodeCheckingResponse();
        qrCodeCheckingResponse.setExsistingFuel(fuelQuotaTracker.getExistingFuel());
        qrCodeCheckingResponse.setVehicleId(vehicle.getId());
        qrCodeCheckingResponse.setVehicleRegistrationNumber(vehicle.getRegistrationNumber());

        System.out.println(qrCodeCheckingResponse);
        return qrCodeCheckingResponse;

    }
}
