package com.fuelmanagement.entity;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class FuelStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private int currentFuelStock; // Liters of fuel available
    @OneToMany(mappedBy = "fuelStation")
    private List<TransactionHistory> distributions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCurrentFuelStock() {
        return currentFuelStock;
    }

    public void setCurrentFuelStock(int currentFuelStock) {
        this.currentFuelStock = currentFuelStock;
    }

    public List<TransactionHistory> getDistributions() {
        return distributions;
    }

    public void setDistributions(List<TransactionHistory> distributions) {
        this.distributions = distributions;
    }
}

