package com.fuelmanagement.controller;

import com.fuelmanagement.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/qr")
public class QRCodeController {

    @Autowired
    private QrCodeService qrCodeService;

    @PostMapping("/generate/{vehicleId}")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable Long vehicleId) {
        try {
            byte[] qrCodeImage = qrCodeService.createQRCode(vehicleId);
            return ResponseEntity.ok()
                    .header("Content-Type", "image/png")
                    .body(qrCodeImage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }


    }

    //test comment 2
}

