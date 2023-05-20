package com.neoris.reto.domain.repository;

import com.neoris.reto.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    public Optional<Usuario> findByEmail(String email);

    public Boolean existsByEmail(String email);
}
