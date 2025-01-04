package com.fuelmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TransactionId;
    @ManyToOne
    @JoinColumn(name = "fuel_station_id")
    private Long fuelStation;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Long vehicle;
    private int fuelAmount; // Liters distributed
    private LocalDateTime distributionDate;

    public Long getTransactionId() {
        return TransactionId;
    }

    public void setId(Long TransactionId) {
        this.TransactionId = TransactionId;
    }

    public Long getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(Long fuelStation) {
        this.fuelStation = fuelStation;
    }

    public Long getVehicle() {
        return vehicle;
    }

    public void setVehicle(Long vehicle) {
        this.vehicle = vehicle;
    }

    public int getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(int fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public LocalDateTime getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(LocalDateTime distributionDate) {
        this.distributionDate = distributionDate;
    }
}
