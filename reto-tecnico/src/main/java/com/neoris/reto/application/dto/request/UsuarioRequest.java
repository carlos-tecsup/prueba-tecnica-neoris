package com.neoris.reto.application.dto.request;

import com.neoris.reto.domain.Rol;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UsuarioRequest {

    private Long id;

    private String email;
    private String password;

    private Set<Rol> roles = new HashSet<>();

}
