package com.fuelmanagement.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {

    public DotenvConfig() {
        Dotenv dotenv = Dotenv.configure().load();
        System.setProperty("TWILIO_ACCOUNT_SID", dotenv.get("TWILIO_ACCOUNT_SID"));
        System.setProperty("TWILIO_AUTH_TOKEN", dotenv.get("TWILIO_AUTH_TOKEN"));
        System.setProperty("TWILIO_PHONE_NUMBER", dotenv.get("TWILIO_PHONE_NUMBER"));
    }
}
