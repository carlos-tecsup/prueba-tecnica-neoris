package com.neoris.reto.application.service;

import com.neoris.reto.application.dto.request.TipoCambioMontoRequest;
import com.neoris.reto.application.dto.request.TipoCambioRequest;
import com.neoris.reto.application.dto.request.TipoCambioDto;

public interface ITipoCambioService {
    void guardarTipoCambio(TipoCambioRequest tipoCambioRequest);
    TipoCambioDto obtenerTipoCambioId(TipoCambioMontoRequest tipoCambioMontoRequest, Long id);
    TipoCambioDto obtenerTipoCambio(TipoCambioMontoRequest tipoCambioMontoRequest);
    void actualizarTipoCambio(TipoCambioRequest tipoCambioRequest , Long id);


}
