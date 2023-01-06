package com.bmwapp.appointment.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class ContextProvider {

    public JwtAuthenticationToken getAuthenticationToken() {

        SecurityContext context = SecurityContextHolder.getContext();
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) context.getAuthentication();
        return authentication;
    }

    public String getLoggedUserId() {
        JwtAuthenticationToken authenticationToken = getAuthenticationToken();
        return authenticationToken.getToken().getId();
    }

    public boolean userHasRole() {
        JwtAuthenticationToken authenticationToken = getAuthenticationToken();

        Map<String, Object> claims = authenticationToken.getToken().getClaims();


        log.info("CLAIMS: {}", claims);
        return true;
    }
}
