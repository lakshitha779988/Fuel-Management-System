package com.fuelmanagement.service.qrCodeScanService;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class QRCodeScanner {

    private final List<QRCodeScanObserver> observers = new ArrayList<>();

    public void addObserver(QRCodeScanObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(QRCodeScanObserver observer) {
        observers.remove(observer);
    }

    public void scanQRCode(String qrCode, float fuelAmount, Long fuelStationId) {
        System.out.println("🚀 Scanning QR Code: " + qrCode);
        for (QRCodeScanObserver observer : observers) {
            observer.onQRCodeScanned(qrCode, fuelAmount,fuelStationId);
        }
    }
}
