package com.fuelmanagement.model.dto.response;

// UserDetailsDTO.java
public class UserDetailsResponse{
    private String firstName;
    private String mobileNumber;

    public UserDetailsResponse(String firstName, String mobileNumber) {
        this.firstName = firstName;
        this.mobileNumber = mobileNumber;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
