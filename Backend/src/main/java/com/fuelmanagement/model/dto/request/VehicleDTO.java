package com.fuelmanagement.model.dto.request;

import com.fuelmanagement.model.entity.Vehicle;

public class VehicleDTO {
    private String chaseNumber;
    private String registrationNumber;
    private String vehicleType;
    private String qrCode;

    // Constructor to map fields from the Vehicle entity
    public VehicleDTO(Vehicle vehicle) {
        this.chaseNumber = vehicle.getChaseNumber();
        this.registrationNumber = vehicle.getRegistrationNumber();
        this.vehicleType = vehicle.getVehicleType().getVehicleTypeName();
        this.qrCode = vehicle.getQrCode() != null ? vehicle.getQrCode().getQrCode() : null;
    }

    // Getters and Setters

    public String getChaseNumber() {
        return chaseNumber;
    }

    public void setChaseNumber(String chaseNumber) {
        this.chaseNumber = chaseNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
