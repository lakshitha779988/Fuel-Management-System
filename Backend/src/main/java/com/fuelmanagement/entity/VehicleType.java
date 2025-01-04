package com.fuelmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeName; // e.g., Car, Van, Bike
    private String fuelType; // e.g., Petrol, Diesel
    private int weeklyQuota; // Weekly fuel quota for this type

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getWeeklyQuota() {
        return weeklyQuota;
    }

    public void setWeeklyQuota(int weeklyQuota) {
        this.weeklyQuota = weeklyQuota;
    }
}
