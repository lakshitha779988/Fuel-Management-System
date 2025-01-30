package com.fuelmanagement.controller;

import com.fuelmanagement.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private SmsService smsService;

    @GetMapping("/test")
    public void send(){
        smsService.sendOtp("+94712954346","778899");
    }
}
