package com.neoris.reto.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JWTResponse {

    private String token;

    private String type="Bearer";

    private String email;
    private List<String> roles;

    public JWTResponse(String jwt, String email, List<String> roles) {
        this.token = jwt;
        this.email = email;
        this.roles = roles;
    }
}
