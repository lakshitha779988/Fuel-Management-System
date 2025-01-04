package com.fuelmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long VehicleId;
    private String VehicleRegistrationNumber;
    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    private VehicleType vehicleType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String qrCode; // QR code generated for the vehicle
    private static int currentQuota; // Remaining fuel quota
    private LocalDateTime resetDate;

    public Long getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        VehicleId = vehicleId;
    }

    public String getVehicleRegistrationNumber() {
        return VehicleRegistrationNumber;
    }

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        VehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static int getCurrentQuota() {
        return currentQuota;
    }

    public void setCurrentQuota(int currentQuota) {
        this.currentQuota = currentQuota;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public LocalDateTime getResetDate() {
        return resetDate;
    }

    public void setResetDate(LocalDateTime resetDate) {
        this.resetDate = resetDate;
    }
}
