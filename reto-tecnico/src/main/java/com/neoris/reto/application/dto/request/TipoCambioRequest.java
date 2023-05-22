package com.neoris.reto.application.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class TipoCambioRequest {
    private String monedaOrigen;
    private String monedaDestino;
    private Double tipoCambio;
    private String usuarioAdicion;
    private String usuarioModificacion;

}
