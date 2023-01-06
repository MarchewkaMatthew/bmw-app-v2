package com.bmwapp.appointment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class PermissionSecurityException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public PermissionSecurityException (String message) {
        super(message);
    }
}