package com.neoris.reto.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoCambioMontoRequest {

    private Double monto;

    @NotEmpty(message = "El campo moneda de Origen no debe ser vacio o nulo")
    private String monedaOrigen;

    @NotEmpty(message = "El campo moneda de Destino no debe ser vacio o nulo")
    private String monedaDestino;
}