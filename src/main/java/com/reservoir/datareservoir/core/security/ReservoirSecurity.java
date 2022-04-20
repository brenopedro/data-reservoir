package com.reservoir.datareservoir.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ReservoirSecurity {

    public Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getClientId() {
        Jwt jwt = (Jwt) authentication().getPrincipal();
        return jwt.getClaimAsString("client_id");
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authentication().getAuthorities();
    }

    public static boolean isGrantAuthorityValid(String authority) {
        return !authority.equals("SCOPE_READ") && !authority.equals("SCOPE_WRITE");
    }
}
