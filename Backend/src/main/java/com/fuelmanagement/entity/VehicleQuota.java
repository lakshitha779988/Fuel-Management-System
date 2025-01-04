package com.fuelmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class VehicleQuota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long VehicleQuotaId;
    private String VehicleQuotaVehicleType;
    private int weeklyQuota;

    public Long getVehicleQuotaId() {
        return VehicleQuotaId;
    }

    public void setVehicleQuotaId(Long vehicleQuotaId) {
        VehicleQuotaId = vehicleQuotaId;
    }

    public String getVehicleQuotaVehicleType() {
        return VehicleQuotaVehicleType;
    }

    public void setVehicleQuotaVehicleType(String vehicleQuotaVehicleType) {
        VehicleQuotaVehicleType = vehicleQuotaVehicleType;
    }

    public int getWeeklyQuota() {
        return weeklyQuota;
    }

    public void setWeeklyQuota(int weeklyQuota) {
        this.weeklyQuota = weeklyQuota;
    }
}
