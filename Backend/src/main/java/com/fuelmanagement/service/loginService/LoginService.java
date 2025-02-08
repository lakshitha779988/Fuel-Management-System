package com.fuelmanagement.service.loginService;

import com.fuelmanagement.model.dto.response.LoginResponse;

public interface LoginService<T> {
    LoginResponse login(T loginDTO);
}
