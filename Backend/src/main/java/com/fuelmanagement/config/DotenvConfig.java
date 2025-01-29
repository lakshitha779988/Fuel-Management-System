package com.fuelmanagement.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class DotenvConfig {

    @PostConstruct
    public void loadEnv() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        Map<String, String> envVars = Map.of(
                "TWILIO_ACCOUNT_SID", dotenv.get("TWILIO_ACCOUNT_SID"),
                "TWILIO_AUTH_TOKEN", dotenv.get("TWILIO_AUTH_TOKEN"),
                "TWILIO_PHONE_NUMBER", dotenv.get("TWILIO_PHONE_NUMBER"),
                "MAILGUN_API_KEY", dotenv.get("MAILGUN_API_KEY"),
                "MAILGUN_DOMAIN", dotenv.get("MAILGUN_DOMAIN"),
                "MAILGUN_FROM", dotenv.get("MAILGUN_FROM")
        );

        envVars.forEach((key, value) -> System.setProperty(key, value));
    }
}
