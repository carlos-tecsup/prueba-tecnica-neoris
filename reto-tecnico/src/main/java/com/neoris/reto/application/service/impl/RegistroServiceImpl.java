package com.neoris.reto.application.service.impl;

import com.neoris.reto.application.dto.request.RegistroRequest;
import com.neoris.reto.application.service.IRegistroService;
import com.neoris.reto.application.util.enums.ERol;
import com.neoris.reto.domain.Rol;
import com.neoris.reto.domain.Usuario;
import com.neoris.reto.domain.repository.RolRepository;
import com.neoris.reto.domain.repository.UsuarioRepository;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RegistroServiceImpl implements IRegistroService {
    private final UsuarioRepository userRepository;
    private final RolServiceImpl rolService;
    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepository;

    @Autowired
    public RegistroServiceImpl(UsuarioRepository userRepository, RolServiceImpl rolService,
                               PasswordEncoder passwordEncoder, RolRepository rolRepository) {
        this.userRepository = userRepository;
        this.rolService = rolService;
        this.passwordEncoder = passwordEncoder;
        this.rolRepository = rolRepository;
    }

    public Single<Boolean> registerUser(RegistroRequest registroRequest) {
        return Single.fromCallable(() -> isEmailRegistered(registroRequest.getEmail()))
                .map(emailRegistered -> {
                    if (emailRegistered) {
                        return false;
                    }
                    Usuario usuario = createUsuario(registroRequest);
                    userRepository.save(usuario);
                    return true;
                });
    }

    private boolean isEmailRegistered(String email) {
        return userRepository.existsByEmail(email);
    }

    private Usuario createUsuario(RegistroRequest registroRequest) {
        Usuario usuario = new Usuario();
        usuario.setEmail(registroRequest.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroRequest.getPassword()));
        Set<Rol> roles = crearRolUsuario(registroRequest.getRol());
        usuario.setRoles(roles);
        return usuario;
    }

    private Set<Rol> crearRolUsuario(Set<String> rol) {
        Set<Rol> roles = new HashSet<>();
        if (Objects.isNull(rol)) {
            roles.add(rolService.crearRolDefault().blockingGet());
        } else {
            roles = rol.stream()
                    .map(elem -> rolRepository.findByName(elem)
                            .orElseGet(() -> rolService.crearRolDefault().blockingGet()))
                    .collect(Collectors.toSet());
        }
        return roles;
    }
}
