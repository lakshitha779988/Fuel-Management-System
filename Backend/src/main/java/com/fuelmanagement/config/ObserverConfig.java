package com.fuelmanagement.config;

import com.fuelmanagement.service.qrCodeScanService.FuelBalanceUpdater;
import com.fuelmanagement.service.qrCodeScanService.FuelLogUpdater;
import com.fuelmanagement.service.qrCodeScanService.NotificationObserver;
import com.fuelmanagement.service.qrCodeScanService.QRCodeScanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObserverConfig {

    @Bean
    public QRCodeScanner fuelStationScanner(FuelLogUpdater fuelLogUpdater,
                                            FuelBalanceUpdater fuelBalanceUpdater,
                                            NotificationObserver notificationObserver) {
        QRCodeScanner scanner = new QRCodeScanner();
        scanner.addObserver(fuelLogUpdater);
        scanner.addObserver(fuelBalanceUpdater);
        scanner.addObserver(notificationObserver);
        return scanner;
    }
}
