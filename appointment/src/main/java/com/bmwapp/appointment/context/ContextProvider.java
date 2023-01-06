package com.bmwapp.appointment.context;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;


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
}
