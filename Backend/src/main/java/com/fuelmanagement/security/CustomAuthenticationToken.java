package com.fuelmanagement.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collection;

public class CustomAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;  // This can be either FuelUser or FuelStation
    private final Object credentials; // The JWT token

    public CustomAuthenticationToken(Object principal, Object credentials, Collection authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(true);
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }
}
