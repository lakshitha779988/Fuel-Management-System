package com.fuelmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class VehicleDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String registrationNumber;
    private boolean isConfirmed; // Whether the vehicle is registered
    private String ownerDetails; // Name and contact of the owner
    private boolean taxPaid; // Current tax status

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public String getOwnerDetails() {
        return ownerDetails;
    }

    public void setOwnerDetails(String ownerDetails) {
        this.ownerDetails = ownerDetails;
    }

    public boolean isTaxPaid() {
        return taxPaid;
    }

    public void setTaxPaid(boolean taxPaid) {
        this.taxPaid = taxPaid;
    }
}
