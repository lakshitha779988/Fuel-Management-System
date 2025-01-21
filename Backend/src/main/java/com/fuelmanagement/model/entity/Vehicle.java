package com.fuelmanagement.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;

import java.util.Date;

@Entity
public class Vehicle  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt;


    @Column(nullable = false)
    private String chaseNumber;

    @Column(nullable = false)
    private String registrationNumber;

    @Column(nullable = false)
    private String fuelType;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private VehicleType vehicleType;

    @OneToOne
    @JoinColumn(name = "fuel_quota_tracker_id", nullable = true)
    private FuelQuotaTracker fuelQuotaTracker;

    @OneToOne
    @JoinColumn(name = "qr_code_id", nullable = true)
    private QrCode qrCode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

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

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public FuelQuotaTracker getFuelQuotaTracker() {
        return fuelQuotaTracker;
    }

    public void setFuelQuotaTracker(FuelQuotaTracker fuelQuotaTracker) {
        this.fuelQuotaTracker = fuelQuotaTracker;
    }

    public QrCode getQrCode() {
        return qrCode;
    }

    public void setQrCode(QrCode qrCode) {
        this.qrCode = qrCode;
    }
}
