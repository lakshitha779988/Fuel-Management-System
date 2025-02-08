package com.fuelmanagement.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


public class LoginResponse {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
