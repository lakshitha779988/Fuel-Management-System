package com.fuelmanagement.service.notificationService;

public interface NotificationService<T> {
    void sendNotification(T notificationData);
}
