package com.fuelmanagement.service;

import com.fuelmanagement.model.entity.QrCode;
import com.fuelmanagement.model.entity.Vehicle;
import com.fuelmanagement.repository.QrCodeRepository;
import com.fuelmanagement.repository.VehicleRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    // Generate a QR code as a PNG and save it to a specified folder
    public byte[] generateQRCodeImage(String qrCodeString, String folderPath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeString, BarcodeFormat.QR_CODE, 300, 300);

        // Ensure the folder exists
        Path folder = Path.of(folderPath);
        if (!Files.exists(folder)) {
            Files.createDirectories(folder);
        }

        // Save the QR code as a PNG file
        String fileName = qrCodeString + ".png"; // Use the unique string as the file name
        Path filePath = folder.resolve(fileName);

        // Write the QR code to the file
        try {
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", filePath);
        } catch (IOException e) {
            throw new IOException("Failed to save QR Code as PNG file.", e);
        }

        // Also return the QR code as a byte array
        try (ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
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

        // Check if a QR code already exists
        if (qrCodeRepository.findByVehicleId(vehicleId).isPresent()) {
            throw new IllegalStateException("QR Code already exists for this vehicle.");
        }

        String qrCodeString = generateUniqueQRCodeString();
        QrCode qrCode = new QrCode(qrCodeString, vehicle);
        qrCodeRepository.save(qrCode);

        // Specify the folder path to save the QR code
        String folderPath = System.getProperty("user.home") + "\\Downloads\\qrcode";
// Replace with your desired folder path
        return generateQRCodeImage(qrCodeString, folderPath);
    }

    public byte[] updateQRCode(Long vehicleId) throws Exception {
        Optional<QrCode> qrCodeOptional = qrCodeRepository.findByVehicleId(vehicleId);

        if (qrCodeOptional.isEmpty()) {
            throw new IllegalArgumentException("QR Code not found for this vehicle.");
        }

        QrCode qrCode = qrCodeOptional.get();
        String newQrCodeString = generateUniqueQRCodeString();
        qrCode.setQrCode(newQrCodeString);
        qrCodeRepository.save(qrCode);

        // Specify the folder path to save the updated QR code
        String folderPath = "C:/QRCodeFiles"; // Replace with your desired folder path
        return generateQRCodeImage(newQrCodeString, folderPath);
    }

    // Delete a QR code record
    public void deleteQRCode(Long vehicleId) {
        if (!qrCodeRepository.findByVehicleId(vehicleId).isPresent()) {
            throw new IllegalArgumentException("QR Code not found for this vehicle.");
        }

        qrCodeRepository.deleteByVehicleId(vehicleId);
    }


}
