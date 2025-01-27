package com.fuelmanagement.model.dto.response;

public class QrCodeCheckingResponse {

    private  String errorMessage;
    private Long vehicleId;
    private float exsistingFuel;
    private String vehicleRegistrationNumber;

    public QrCodeCheckingResponse() {}

    public QrCodeCheckingResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public float getExsistingFuel() {
        return exsistingFuel;
    }

    public void setExsistingFuel(float exsistingFuel) {
        this.exsistingFuel = exsistingFuel;
    }

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }
}
