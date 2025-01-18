package com.fuelmanagement.model.dto.request;


import org.springframework.stereotype.Component;

@Component
public class LoggingRequest {
   private String mobileNumber;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
