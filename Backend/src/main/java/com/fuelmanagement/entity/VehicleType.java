package com.fuelmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long VehicleTypeId;
    private String VehicleTypeTypeName; // e.g., Car, Van, Bike
    private String VehicleFuelType; // e.g., Petrol, Diesel
    private int weeklyQuota; // Weekly fuel quota for this type

    public Long getVehicleTypeId() {
        return VehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        VehicleTypeId = vehicleTypeId;
    }

    public String getVehicleTypeTypeName() {
        return VehicleTypeTypeName;
    }

    public void setVehicleTypeTypeName(String vehicleTypeTypeName) {
        VehicleTypeTypeName = vehicleTypeTypeName;
    }

    public String getVehicleFuelType() {
        return VehicleFuelType;
    }

    public void setVehicleFuelType(String vehicleFuelType) {
        VehicleFuelType = vehicleFuelType;
    }

    public int getWeeklyQuota() {
        return weeklyQuota;
    }

    public void setWeeklyQuota(int weeklyQuota) {
        this.weeklyQuota = weeklyQuota;
    }
}
