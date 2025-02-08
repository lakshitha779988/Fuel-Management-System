package com.fuelmanagement.service.notificationService;

import com.fuelmanagement.model.dto.EmailNotificationDTO;
import com.fuelmanagement.model.dto.SMSNotificationDTO;
import com.fuelmanagement.service.notificationService.NotificationFactory;
import com.fuelmanagement.service.notificationService.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationManager {

    private final NotificationFactory notificationFactory;

    public NotificationManager(NotificationFactory notificationFactory) {
        this.notificationFactory = notificationFactory;
    }

    public void notifyUser(String email, String mobile, String message) {
        if (email != null && !email.isEmpty()) {
            NotificationService<EmailNotificationDTO> service = (NotificationService<EmailNotificationDTO>) notificationFactory.getNotificationService("email");
            service.sendNotification(new EmailNotificationDTO(email, "Fuel Management Notification", message));
        }
        if (mobile != null && !mobile.isEmpty()) {
            NotificationService<SMSNotificationDTO> service = (NotificationService<SMSNotificationDTO>) notificationFactory.getNotificationService("sms");
            service.sendNotification(new SMSNotificationDTO(mobile, message));
        } else {
            throw new IllegalArgumentException("User must have either an email or mobile number.");
        }
    }
}
