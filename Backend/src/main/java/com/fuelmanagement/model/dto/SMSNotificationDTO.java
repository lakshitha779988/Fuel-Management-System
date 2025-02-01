package com.fuelmanagement.model.dto;

public class SMSNotificationDTO {
    private String mobileNumber;
    private String message;

    public SMSNotificationDTO(String mobileNumber, String message) {
        this.mobileNumber = mobileNumber;
        this.message = message;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getMessage() {
        return message;
    }
}
