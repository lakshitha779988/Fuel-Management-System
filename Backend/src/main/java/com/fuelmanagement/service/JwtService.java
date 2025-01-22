package com.fuelmanagement.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("your_secret_key_your_secret_key_your_secret_key".getBytes());
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    // Generate token with identifier (name or mobileNumber), type, and role
    public String generateToken(String identifier, String type, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("identifier", identifier); // Can be name or mobileNumber
        claims.put("type", type);
        claims.put("role", role);
        return createToken(claims);
    }

    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256) // Updated to use SecretKey
                .compact();
    }

    // Extract claims
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractIdentifier(String token) {
        return extractAllClaims(token).get("identifier", String.class);
    }

    public String extractType(String token) {
        return extractAllClaims(token).get("type", String.class);
    }

    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // Verify token validity
    public boolean verifyToken(String token, String expectedType) {
        try {
            Claims claims = extractAllClaims(token);

            // Validate the token is not expired
            if (claims.getExpiration().before(new Date())) {
                return false;
            }

            // Validate the token type (e.g., FUEL_USER or FUEL_STATION)
            String type = claims.get("type", String.class);
            return type != null && type.equals(expectedType);

        } catch (JwtException | IllegalArgumentException e) {
            // Handle cases of invalid tokens or parsing issues
            System.err.println("Token verification failed: " + e.getMessage());
            return false;
        }
    }

    // Token validation
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
