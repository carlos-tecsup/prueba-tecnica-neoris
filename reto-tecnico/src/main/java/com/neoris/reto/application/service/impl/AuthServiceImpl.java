package com.neoris.reto.application.service.impl;
import com.neoris.reto.application.configuration.security.jwt.JwtUtils;
import com.neoris.reto.application.dto.request.LoginRequest;
import com.neoris.reto.application.dto.response.JWTResponse;
import com.neoris.reto.application.service.IAuthService;
import com.neoris.reto.application.service.IRegistroService;
import com.neoris.reto.application.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public JWTResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = performAuthentication(loginRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UsuarioService userDetails = (UsuarioService) authentication.getPrincipal();
        String jwt = generateJwtToken(authentication);
        List<String> roles = extractRoles(userDetails);
        return new JWTResponse(jwt, userDetails.getEmail(), roles);
    }

    private Authentication performAuthentication(LoginRequest loginRequest) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
    }

    private String generateJwtToken(Authentication authentication) {
        return jwtUtils.generateJwtToken(authentication);
    }


    private List<String> extractRoles(UsuarioService userDetails) {
        return userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
