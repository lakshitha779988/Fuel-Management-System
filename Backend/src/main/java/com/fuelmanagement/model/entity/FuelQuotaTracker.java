package com.fuelmanagement.model.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Date;


@Entity
@Component
public class FuelQuotaTracker {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt;

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

    @Column(nullable = false)
    private Float existingFuel;

    @Column(nullable = false)
    private Date resetDate;

    @Column(nullable = false)
    private Float weeklyConsumption;

    public Float getExistingFuel() {
        return existingFuel;
    }

    public void setExistingFuel(Float existingFuel) {
        this.existingFuel = existingFuel;
    }

    public Date getResetDate() {
        return resetDate;
    }

    public void setResetDate(Date resetDate) {
        this.resetDate = resetDate;
    }

    public Float getWeeklyConsumption() {
        return weeklyConsumption;
    }

    public void setWeeklyConsumption(Float weeklyConsumption) {
        this.weeklyConsumption = weeklyConsumption;
    }
}
