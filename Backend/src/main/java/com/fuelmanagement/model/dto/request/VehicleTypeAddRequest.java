package com.fuelmanagement.model.dto.request;

public class VehicleTypeAddRequest {

    String name;
    int quota;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }
}
