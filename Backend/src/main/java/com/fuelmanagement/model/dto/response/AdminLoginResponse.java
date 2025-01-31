package com.fuelmanagement.model.dto.response;

public class AdminLoginResponse {

    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AdminLoginResponse(String token) {
        this.token = token;
    }
}
