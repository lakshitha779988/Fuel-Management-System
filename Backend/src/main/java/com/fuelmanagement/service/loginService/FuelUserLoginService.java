package com.fuelmanagement.service.loginService;

import com.fuelmanagement.model.dto.request.FuelUserLoginRequest;
import com.fuelmanagement.model.dto.response.LoginResponse;
import com.fuelmanagement.model.entity.mysql.User;
import com.fuelmanagement.repository.mysql.UserRepository;
import com.fuelmanagement.service.FirebaseTokenService;
import com.fuelmanagement.service.JwtService;
import com.fuelmanagement.service.notificationService.NotificationContetCreationService;
import com.fuelmanagement.service.notificationService.NotificationManager;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FuelUserLoginService implements LoginService<FuelUserLoginRequest> {

    private final FirebaseTokenService firebaseTokenService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final NotificationManager notificationManager;
    private final NotificationContetCreationService notificationContetCreationService;


    @Autowired
    public FuelUserLoginService(FirebaseTokenService firebaseTokenService, UserRepository userRepository, JwtService jwtService, NotificationManager notificationManager, NotificationContetCreationService notificationContetCreationService) {
        this.firebaseTokenService = firebaseTokenService;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.notificationManager = notificationManager;
        this.notificationContetCreationService = notificationContetCreationService;
    }


    @Override
    public LoginResponse login(FuelUserLoginRequest fuelUserLoginRequest) {
        try {

            FirebaseToken decodedToken = firebaseTokenService.verifyToken(fuelUserLoginRequest.getFirebaseToken());


            User fuelUser = userRepository.findByMobileNumber(fuelUserLoginRequest.getMobileNumber())
                    .orElseThrow(() -> new UsernameNotFoundException("Mobile number not registered"));

            // Step 3: Generate JWT token
            String token = jwtService.generateToken(fuelUser.getMobileNumber(), "FUEL_USER","citizen");


            // Step 4: Return response
            LocalDateTime loginTime = LocalDateTime.now();

            String emailContent = notificationContetCreationService.generateUserLoginNotificationEmailContent(fuelUser.getFirstName(),loginTime);
            notificationManager.notifyUser(fuelUser.getEmail(),fuelUser.getMobileNumber(), emailContent);
            return new LoginResponse(token);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Invalid Firebase token", e);
        }
    }
}
