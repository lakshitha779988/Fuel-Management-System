package com.fuelmanagement.model.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id ;

     private Date createdAt;

    @Column(nullable = false)
     private int fuelLimit;

    @Column(nullable = false)
     private String VehicleTypeName;

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

    public int getFuelLimit() {
        return fuelLimit;
    }

    public void setFuelLimit(int fuelLimit) {
        this.fuelLimit = fuelLimit;
    }

    public String getVehicleTypeName() {
        return VehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        VehicleTypeName = vehicleTypeName;
    }
}