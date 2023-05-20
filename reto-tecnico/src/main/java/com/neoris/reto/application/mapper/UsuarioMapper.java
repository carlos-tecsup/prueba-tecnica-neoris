package com.neoris.reto.application.mapper;

import com.neoris.reto.application.dto.request.UsuarioRequest;
import com.neoris.reto.domain.Usuario;
import org.mapstruct.Mapper;

@Mapper
public interface UsuarioMapper {
    Usuario toModel(UsuarioRequest usuarioRequest);
}