package com.fuelmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "fuel_station_id")
    private FuelStation fuelStation;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    private int fuelAmount; // Liters distributed
    private LocalDateTime distributionDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FuelStation getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(FuelStation fuelStation) {
        this.fuelStation = fuelStation;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
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
