package com.neoris.reto.application.mapper.impl;

import com.neoris.reto.application.dto.request.TipoCambioRequest;
import com.neoris.reto.application.mapper.ITipoCambioMapper;
import com.neoris.reto.domain.TipoCambio;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ITipoCambioMapperImpl implements ITipoCambioMapper {
    @Override
    public TipoCambio toModel(TipoCambioRequest tipoCambioRequest) {
        return TipoCambio.builder()
                .tipoCambio(tipoCambioRequest.getTipoCambio())
                .monedaDestino(tipoCambioRequest.getMonedaDestino())
                .monedaOrigen(tipoCambioRequest.getMonedaOrigen())
                .usuarioAdicion(tipoCambioRequest.getUsuarioAdicion())
                .fechaAdicion(new Date()).build();
    }
}
