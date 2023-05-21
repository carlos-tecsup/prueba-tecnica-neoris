package com.neoris.reto.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class TipoCambioNotFoundException extends RuntimeException {

    public TipoCambioNotFoundException(String message) {
        super(message);
    }

    public TipoCambioNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

