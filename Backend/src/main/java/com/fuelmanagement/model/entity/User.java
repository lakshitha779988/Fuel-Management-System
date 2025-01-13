package com.fuelmanagement.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Data createAt;
    @Column(nullable = false)
    private String mobileNumber;

    private String password;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique  = true)
    private String nationalId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Data getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Data createAt) {
        this.createAt = createAt;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}
