package com.fuelmanagement.service.qrCodeScanService;

import com.fuelmanagement.model.entity.mysql.FuelLog;
import com.fuelmanagement.repository.mysql.FuelLogRepository;
import com.fuelmanagement.service.entityService.FuelStationService;
import com.fuelmanagement.service.entityService.QrCodeService;

import java.util.Date;

public class FuelLogUpdater implements QRCodeScanObserver{

    private final FuelLogRepository fuelLogRepository;
    private final QrCodeService qrCodeService;
    private final FuelStationService fuelStationService;

    public FuelLogUpdater(FuelLogRepository fuelLogRepository, QrCodeService qrCodeService, FuelStationService fuelStationService) {
        this.fuelLogRepository = fuelLogRepository;
        this.qrCodeService = qrCodeService;
        this.fuelStationService = fuelStationService;
    }

    @Override
    public void onQRCodeScanned(String qrCode, float fuelAmount , Long fuelStationId) {

        FuelLog fuelLog = new FuelLog();
        fuelLog.setFuelAmount(fuelAmount);
        fuelLog.setFuelStation(fuelStationService.getFuelStationById(fuelStationId));
        fuelLog.setUser(qrCodeService.getUserForQRString(qrCode));
        fuelLog.setVehicle(qrCodeService.getUserForQRString(qrCode).getVehicle());
        fuelLog.setCreatedAt(new Date(System.currentTimeMillis()));
        fuelLog.setTransactionTime(new Date(System.currentTimeMillis()));

        fuelLogRepository.save(fuelLog);

    }
}
