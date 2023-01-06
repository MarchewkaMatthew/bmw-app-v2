package com.bmwapp.appointment.context;

import com.bmwapp.appointment.exception.PermissionSecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        return authenticationToken.getToken().getClaims().get("sub").toString();
    }

    public Map<String, Object> getLoggedUserClaims() {
        JwtAuthenticationToken authenticationToken = getAuthenticationToken();
        Map<String, Object> claims = authenticationToken.getToken().getClaims();
        return claims;
    }

    public boolean userHasRole(String roleName) {
        JwtAuthenticationToken authenticationToken = getAuthenticationToken();
        Map<String, Object> claims = authenticationToken.getToken().getClaims();
        ArrayList<String> roles = (ArrayList<String>) claims.get("roles");
        return roles.contains(roleName);
    }

    public void requiredRole(String roleName) {
        if(!userHasRole(roleName)) throw new PermissionSecurityException("The user does not have permission to the resource!");
    }

    public void requiredRoles(String... roles) {
        
        if(!userHasRole(roleName)) throw new PermissionSecurityException("The user does not have permission to the resource!");
    }
}
