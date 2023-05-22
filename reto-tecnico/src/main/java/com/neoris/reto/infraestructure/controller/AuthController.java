package com.neoris.reto.infraestructure.controller;

import com.neoris.reto.application.configuration.security.jwt.JwtUtils;
import com.neoris.reto.application.dto.request.LoginRequest;
import com.neoris.reto.application.dto.request.RegistroRequest;
import com.neoris.reto.application.dto.response.JWTResponse;
import com.neoris.reto.application.dto.response.MensajeResponse;
import com.neoris.reto.application.service.IAuthService;
import com.neoris.reto.application.service.IRegistroService;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IRegistroService registroService;

    @Autowired
    private IAuthService authService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("login")
    public Single<ResponseEntity<JWTResponse>> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping("registrar-usuario")
    public Single<ResponseEntity<?>> registerUser(@RequestBody RegistroRequest registroRequest) {
        return Single.fromCallable(() -> registroService.registerUser(registroRequest))
                .flatMap(usuarioRegistrado -> {
                    if (usuarioRegistrado.blockingGet()) {
                        return Single.just(ResponseEntity.ok(new MensajeResponse("Registrado")));
                    } else {
                        return Single.just(ResponseEntity.badRequest().body(new MensajeResponse("El email ya está registrado")));
                    }
                });
    }


}
