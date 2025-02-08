package com.fuelmanagement.service.notificationService;

import com.fuelmanagement.service.notificationService.EmailNotificationService;
import com.fuelmanagement.service.notificationService.NotificationService;
import com.fuelmanagement.service.notificationService.SMSNotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationFactory {

    private final EmailNotificationService emailNotificationService;
    private final SMSNotificationService smsNotificationService;

    public NotificationFactory(EmailNotificationService emailNotificationService, SMSNotificationService smsNotificationService) {
        this.emailNotificationService = emailNotificationService;
        this.smsNotificationService = smsNotificationService;
    }

    public NotificationService<?> getNotificationService(String contactType) {
        if ("email".equalsIgnoreCase(contactType)) {
            return emailNotificationService;
        } else if ("sms".equalsIgnoreCase(contactType)) {
            return smsNotificationService;
        } else {
            throw new IllegalArgumentException("Invalid notification type: " + contactType);
        }
    }
}
