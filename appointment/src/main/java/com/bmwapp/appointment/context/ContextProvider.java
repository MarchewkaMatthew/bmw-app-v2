package com.bmwapp.appointment.context;

import com.bmwapp.appointment.exception.PermissionSecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    public List<String> getUserRoles() {
        List<String> userRoles = (List<String>) getLoggedUserClaims().get("roles");
        if(userRoles != null) return userRoles;
        else throw new PermissionSecurityException("The user does not have any permission!");
    }
    public boolean userHasRole(String roleName) {
        return getUserRoles().contains(roleName);
    }

    public void requiredRole(String roleName) {
        if(!userHasRole(roleName)) throw new PermissionSecurityException("The user does not have permission to the resource!");
    }

    public void requiredRoles(String... roles) {
        if(!getUserRoles().containsAll(Arrays.asList(roles))) throw new PermissionSecurityException("The user does not have permission to the resource!");
    }

    public void requiredAnyRoles(String... roles) {
        if(!userHasAnyRoles(Arrays.asList(roles))) throw new PermissionSecurityException("The user does not have permission to the resource!");
    }

    public boolean userHasAnyRoles(List<String> roles) {
        for (String role : roles) {
            for (String userRole : getUserRoles()) {
                if(role.equals(userRole)) return true;
            }
        }
        return false;
    }
}
