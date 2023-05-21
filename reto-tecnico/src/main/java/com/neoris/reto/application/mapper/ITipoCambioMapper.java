package com.neoris.reto.application.mapper;

import com.neoris.reto.application.dto.request.TipoCambioRequest;
import com.neoris.reto.domain.TipoCambio;
import org.springframework.stereotype.Component;

public interface ITipoCambioMapper {

    TipoCambio toModel(TipoCambioRequest tipoCambioRequest);
}
