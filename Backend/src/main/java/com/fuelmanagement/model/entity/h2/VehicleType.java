package com.fuelmanagement.model.entity.h2;

import jakarta.persistence.GenerationType;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle_type")
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_type_id")
    private Integer vehicleTypeId;

    @Column(name = "vehicle_type_name", nullable = false)
    private String vehicleTypeName;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "max_weight")
    private Integer maxWeight;

    @Column(name = "max_passengers")
    private Integer maxPassengers;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Getters and Setters
    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Integer maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Integer getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(Integer maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
