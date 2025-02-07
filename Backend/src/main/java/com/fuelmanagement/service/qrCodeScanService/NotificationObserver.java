package com.fuelmanagement.service.qrCodeScanService;

import com.fuelmanagement.model.entity.mysql.FuelStation;
import com.fuelmanagement.model.entity.mysql.User;
import com.fuelmanagement.service.entityService.FuelStationService;
import com.fuelmanagement.service.entityService.QrCodeService;
import com.fuelmanagement.service.notificationService.NotificationContetCreationService;
import com.fuelmanagement.service.notificationService.NotificationManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationObserver implements QRCodeScanObserver{

    private final NotificationManager notificationManager;
    private final QrCodeService qrCodeService;
    private final NotificationContetCreationService notificationContetCreationService;
    private final FuelStationService fuelStationService;

    public NotificationObserver(NotificationManager notificationManager, QrCodeService qrCodeService, NotificationContetCreationService notificationContetCreationService, FuelStationService fuelStationService) {
        this.notificationManager = notificationManager;
        this.qrCodeService = qrCodeService;
        this.notificationContetCreationService = notificationContetCreationService;
        this.fuelStationService = fuelStationService;
    }

    @Override
    public void onQRCodeScanned(String qrCode, float fuelAmount , Long fuelStationId) {

        FuelStation fuelStation = fuelStationService.getFuelStationById(fuelStationId);

        System.out.println("come to notification update");

      User user = qrCodeService.getUserForQRString(qrCode);
        LocalDateTime localDateTime = LocalDateTime.now();
      String content = notificationContetCreationService.generateFuelLimitUpdateEmailContent(fuelStation.getName(),fuelAmount,localDateTime);

        notificationManager.notifyUser(user.getEmail(),user.getMobileNumber(),content);
    }
}
