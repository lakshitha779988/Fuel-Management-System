package com.fuelmanagement.controller;

import com.fuelmanagement.model.dto.response.QrCodeCheckingResponse;
import com.fuelmanagement.service.JwtService;
import com.fuelmanagement.service.entityService.QrCodeService;
import com.fuelmanagement.service.entityService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/qr")
public class QRCodeController {


    private final QrCodeService qrCodeService;
    private final JwtService  jwtService;
    private final UserService userService;

    @Autowired
    public QRCodeController(QrCodeService qrCodeService, JwtService jwtService, UserService userService) {
        this.qrCodeService = qrCodeService;
        this.jwtService = jwtService;
        this.userService = userService;
    }


    //Generate QrCode
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/generate")
    public ResponseEntity<byte[]> generateQRCode(@RequestParam String token) {

        System.out.println(token);
        String mobileNumber = jwtService.extractIdentifier(token);
        System.out.println(mobileNumber);
        Long vehicleId = userService.findVehicleIdByMobileNumber(mobileNumber);
        System.out.println(vehicleId);
        try {
            byte[] qrCodeImage = qrCodeService.createQRCode(vehicleId);
            return ResponseEntity.ok()
                    .header("Content-Type", "image/png")
                    .body(qrCodeImage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }


    }

    //update QrCode
    @GetMapping("/update")
    public ResponseEntity<byte[]> updateQRCode(@RequestParam String token) {


        try {
            byte[] qrCodeImage = qrCodeService.updateQRCode(token);
            return ResponseEntity.ok()
                    .header("Content-Type", "image/png")
                    .body(qrCodeImage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    //Check is the Qr code String in the system or not
    @PostMapping("/check-qr-string")
    public ResponseEntity<QrCodeCheckingResponse> checkQrCode(@RequestBody Map<String, String> payload) {
        String qrCode = payload.get("qrString");
        System.out.println(qrCode);// Get the qrString from the body
        try {
            // Call service method to check if the QR code is valid
            QrCodeCheckingResponse response = qrCodeService.checkIsQrValid(qrCode);
            System.out.println(response);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            // Handle the case where the QR code is invalid or no vehicle is associated
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new QrCodeCheckingResponse("QR Code or Vehicle not found: " + ex.getMessage()));
        }
    }
}