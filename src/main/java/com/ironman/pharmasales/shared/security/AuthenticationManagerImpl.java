package com.ironman.pharmasales.shared.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationManagerImpl implements AuthenticationManager {

    private final JwtHelper jwtHelper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getCredentials().toString();
        String username = jwtHelper.getUserNameFromToken(token);

        if (username == null && !jwtHelper.validateToken(token))
            throw new AuthenticationServiceException("Token not valid or expired");

        return new UsernamePasswordAuthenticationToken(
                username,
                null,
                authentication.getAuthorities()
        );
    }
}
