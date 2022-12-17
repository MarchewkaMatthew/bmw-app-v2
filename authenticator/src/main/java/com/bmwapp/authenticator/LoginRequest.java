package com.bmwapp.authenticator;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
