package com.neoris.reto.application.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {
    @Email
    private String email;

    @NotEmpty
    private String password;
}
