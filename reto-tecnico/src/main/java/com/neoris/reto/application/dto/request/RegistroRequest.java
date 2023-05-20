package com.neoris.reto.application.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class RegistroRequest {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 6, max = 40)
    private String password;

    private Set<String> rol;
}
