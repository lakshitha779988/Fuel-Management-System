package com.fuelmanagement.controller;

import com.fuelmanagement.service.JwtService;
import com.fuelmanagement.service.QrCodeService;
import com.fuelmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/qr")
public class QRCodeController {

    @Autowired
    private QrCodeService qrCodeService;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/generate")
    public ResponseEntity<byte[]> generateQRCode(@RequestParam String token) {

        System.out.println(token);
        String mobileNumber =  jwtService.extractIdentifier(token);
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
    @PutMapping("/update/{vehicleId}")
    public ResponseEntity<byte[]> updateQRCode(@PathVariable Long vehicleId) {
        try {
            byte[] qrCodeImage = qrCodeService.updateQRCode(vehicleId);
            return ResponseEntity.ok()
                    .header("Content-Type", "image/png")
                    .body(qrCodeImage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{vehicleId}")
    public ResponseEntity<String> deleteQRCode(@PathVariable Long vehicleId) {
        try {
            qrCodeService.deleteQRCode(vehicleId);
            return ResponseEntity.ok("QR Code deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

