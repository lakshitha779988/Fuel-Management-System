package com.fuelmanagement.service.qrCodeScanService;

import com.fuelmanagement.model.entity.mysql.User;
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

    public NotificationObserver(NotificationManager notificationManager, QrCodeService qrCodeService, NotificationContetCreationService notificationContetCreationService) {
        this.notificationManager = notificationManager;
        this.qrCodeService = qrCodeService;
        this.notificationContetCreationService = notificationContetCreationService;
    }

    @Override
    public void onQRCodeScanned(String qrCode, float fuelAmount , Long fuelStationId) {

      User user = qrCodeService.getUserForQRString(qrCode);
        LocalDateTime localDateTime = LocalDateTime.now();
      String content = notificationContetCreationService.generateQRCodeUpdateEmailContent(user.getVehicle().getRegistrationNumber(),localDateTime);

        notificationManager.notifyUser(user.getEmail(),user.getMobileNumber(),content);
    }
}
