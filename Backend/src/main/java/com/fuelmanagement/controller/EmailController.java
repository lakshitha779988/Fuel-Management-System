package com.fuelmanagement.controller;

import com.fuelmanagement.model.dto.request.EmailRequest;
import com.fuelmanagement.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        return emailService.sendEmail(
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getText()
        );
    }
}
