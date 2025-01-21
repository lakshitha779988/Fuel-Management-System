package com.fuelmanagement.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseTokenService {

    @Autowired
    private FirebaseAuth firebaseAuth;

    public FirebaseTokenService(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    public FirebaseToken verifyToken(String idToken) throws FirebaseAuthException {
        return firebaseAuth.verifyIdToken(idToken);
    }
}
