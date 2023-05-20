package com.neoris.reto.infraestructure.controller;


import com.neoris.reto.application.configuration.security.jwt.JwtUtils;
import com.neoris.reto.application.dto.request.LoginRequest;
import com.neoris.reto.application.dto.request.RegistroRequest;
import com.neoris.reto.application.dto.response.JWTResponse;
import com.neoris.reto.application.dto.response.MessageResponse;
import com.neoris.reto.application.service.IAuthService;
import com.neoris.reto.application.service.IRegistroService;
import com.neoris.reto.application.service.impl.AuthServiceImpl;
import com.neoris.reto.application.service.impl.RegistroServiceImpl;
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
    public ResponseEntity<JWTResponse> authenticateUser(@RequestBody LoginRequest loginRequest){
        JWTResponse response = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(response);
    }


    @PostMapping("registrar-usuario")
    public ResponseEntity<?> registerUser(@RequestBody RegistroRequest registroRequest){
        boolean usuarioRegistrado = registroService.registerUser(registroRequest);
        if (usuarioRegistrado){
            return ResponseEntity.ok(new MessageResponse("Registrado"));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("El email ya está registrado"));

        }
    }
}
