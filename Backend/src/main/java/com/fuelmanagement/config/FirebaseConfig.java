package com.fuelmanagement.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        // Path to the service account key JSON file
        String serviceAccountPath = "src/main/resources/firebase-service-account.json";

        // Load the service account key
        FileInputStream serviceAccount = new FileInputStream(serviceAccountPath);

        // Initialize Firebase Options
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        // Initialize Firebase App
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

        // Return FirebaseAuth instance
        return FirebaseAuth.getInstance();
    }
}
