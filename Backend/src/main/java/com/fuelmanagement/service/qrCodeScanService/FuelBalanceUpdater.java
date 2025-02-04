package com.fuelmanagement.service.qrCodeScanService;

import com.fuelmanagement.model.entity.mysql.FuelQuotaTracker;
import com.fuelmanagement.repository.mysql.FuelQuotaTrackerRepository;
import com.fuelmanagement.service.entityService.QrCodeService;
import com.google.api.gax.rpc.InvalidArgumentException;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.stereotype.Service;


@Service
public class FuelBalanceUpdater implements QRCodeScanObserver{

    private final QrCodeService qrCodeService;
    private final FuelQuotaTrackerRepository fuelQuotaTrackerRepository;

    public FuelBalanceUpdater(QrCodeService qrCodeService, FuelQuotaTrackerRepository fuelQuotaTrackerRepository) {
        this.qrCodeService = qrCodeService;
        this.fuelQuotaTrackerRepository = fuelQuotaTrackerRepository;
    }

    @Override
    public void onQRCodeScanned(String qrCode, float fuelAmount , Long fuelStationId) {

       FuelQuotaTracker fuelQuotaTracker= qrCodeService.getFuelQuotaTrackerForQrString(qrCode);
       if(fuelAmount>fuelQuotaTracker.getExistingFuel()){
           throw new IllegalArgumentException("FuelAmount is need to be less than this");
       }

       float newFuelAmount = fuelQuotaTracker.getExistingFuel() - fuelAmount;

       fuelQuotaTracker.setExistingFuel(newFuelAmount);
       fuelQuotaTrackerRepository.save(fuelQuotaTracker);
    }
}
