package com.fuelmanagement.model.dto.request;


import org.springframework.stereotype.Component;

@Component
public class RegistrationRequest {


    private String mobileNumber;

    private String firstName;

    private String lastName;

    private String address;



    private String nationalId;


    private String chaseNumber;


    private String vehicleNumber;

    private Long vehicleTypeId;

    private String fuelType;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }




    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getChaseNumber() {
        return chaseNumber;
    }

    public void setChaseNumber(String chaseNumber) {
        this.chaseNumber = chaseNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String registrationNumber) {
        this.vehicleNumber = registrationNumber;
    }

    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "mobileNumber='" + mobileNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", chaseNumber='" + chaseNumber + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleTypeId=" + vehicleTypeId +
                ", fuelType='" + fuelType + '\'' +
                '}';
    }
}
