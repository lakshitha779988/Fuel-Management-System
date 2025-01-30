package com.fuelmanagement.model.dto.response;

import java.time.LocalDateTime;

public class FuelTransactionDTO {

    private LocalDateTime transactionTime;
    private Float fuelAmount;
    private String fuelStationName;

    public FuelTransactionDTO(LocalDateTime transactionTime, Float fuelAmount, String fuelStationName) {
        this.transactionTime = transactionTime;
        this.fuelAmount = fuelAmount;
        this.fuelStationName = fuelStationName;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Float getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(Float fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public String getFuelStationName() {
        return fuelStationName;
    }

    public void setFuelStationName(String fuelStationName) {
        this.fuelStationName = fuelStationName;
    }
}
