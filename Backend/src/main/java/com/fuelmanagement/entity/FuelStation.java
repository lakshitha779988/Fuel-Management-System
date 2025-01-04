package com.fuelmanagement.entity;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class FuelStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long FuelStationId;
    private String FuelStationName;
    private String FuelStationLocation;
    private int FuelStationFuelStationCurrentFuelStock; // Liters of fuel available
    @OneToMany(mappedBy = "fuelStation")
    private List<TransactionHistory> distributions;

    public Long getFuelStationId() {
        return FuelStationId;
    }

    public void setFuelStationId(Long fuelStationId) {
        FuelStationId = fuelStationId;
    }

    public String getFuelStationName() {
        return FuelStationName;
    }

    public void setFuelStationName(String fuelStationName) {
        FuelStationName = fuelStationName;
    }

    public String getFuelStationLocation() {
        return FuelStationLocation;
    }

    public void setFuelStationLocation(String fuelStationLocation) {
        FuelStationLocation = fuelStationLocation;
    }

    public int getFuelStationFuelStationCurrentFuelStock() {
        return FuelStationFuelStationCurrentFuelStock;
    }

    public void setFuelStationFuelStationCurrentFuelStock(int fuelStationFuelStationCurrentFuelStock) {
        FuelStationFuelStationCurrentFuelStock = fuelStationFuelStationCurrentFuelStock;
    }

    public List<TransactionHistory> getDistributions() {
        return distributions;
    }

    public void setDistributions(List<TransactionHistory> distributions) {
        this.distributions = distributions;
    }
}

