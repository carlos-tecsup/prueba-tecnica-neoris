package com.neoris.reto.application.service;

import com.neoris.reto.application.dto.request.LoginRequest;
import com.neoris.reto.application.dto.response.JWTResponse;
import io.reactivex.Single;

public interface IAuthService {
    Single<JWTResponse> authenticateUser(LoginRequest loginRequest);
}
