package com.neoris.reto.application.dto.request;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TipoCambioDTO {
    private Long id;
    private String monedaOrigen;
    private String monedaDestino;
    private Double tipoCambio;
    private String usuario;
    private Double monto;
    private Double montoTipoCambio;
}
