package com.neoris.reto.domain.repository;


import com.neoris.reto.application.dto.request.RolRequest;
import com.neoris.reto.application.util.enums.ERol;
import com.neoris.reto.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  RolRepository extends JpaRepository<Rol, Long> {
    public Optional<Rol> findByName(String name);

}
