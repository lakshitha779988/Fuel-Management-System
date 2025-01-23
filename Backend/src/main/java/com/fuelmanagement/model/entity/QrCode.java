package com.fuelmanagement.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class QrCode  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt;

    private String qrCode; // The actual QR code string

    @OneToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    // Constructors
    public QrCode() {}

    public QrCode(String qrCode, Vehicle vehicle) {
        this.qrCode = qrCode;
        this.vehicle = vehicle;
    }


    //getter and setters

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Vehicle getVehicle() { return vehicle;}

    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }
}
