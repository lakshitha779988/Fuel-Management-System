package com.fuelmanagement.service.qrCodeScanService;

public interface QRCodeScanObserver {
    void onQRCodeScanned(String qrCode, float fuelAmount , Long fuelStationId);

}
