package com.neoris.reto.application.service;

import com.neoris.reto.application.dto.request.LoginRequest;
import com.neoris.reto.application.dto.response.JWTResponse;

public interface IAuthService {
    JWTResponse authenticateUser(LoginRequest loginRequest);

}
