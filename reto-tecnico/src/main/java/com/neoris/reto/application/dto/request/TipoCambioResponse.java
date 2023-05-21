package com.neoris.reto.application.dto.request;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TipoCambioResponse {
    @JsonProperty("monto")
    private Double monto;

    @JsonProperty("montoTipoCambio")
    private Double montoTipoCambio;

    @JsonProperty("monedaOrigen")
    private String monedaOrigen;

    @JsonProperty("monedaDestino")
    private String monedaDestino;

    @JsonProperty("tipoCambio")
    private Double tipoCambio;

    @JsonProperty("usuario")
    private String usuario;
}
