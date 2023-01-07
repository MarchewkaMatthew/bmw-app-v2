package com.bmwapp.appointment.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

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

    public boolean userHasRole(String roleName) {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) getAuthenticationToken().getAuthorities();
        log.info("AUTHORITIES with list {}", authorities);
        for (GrantedAuthority grantedAuthority : authorities) {
            if (roleName.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }
        return false;
    }

}
