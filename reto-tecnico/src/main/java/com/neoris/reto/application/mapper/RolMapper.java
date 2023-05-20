package com.neoris.reto.application.mapper;

import com.neoris.reto.application.dto.request.RolRequest;
import com.neoris.reto.application.dto.request.UsuarioRequest;
import com.neoris.reto.domain.Rol;
import com.neoris.reto.domain.Usuario;
import org.mapstruct.Mapper;

@Mapper
public interface RolMapper {
    Rol toModel(RolRequest rolRequest);
}