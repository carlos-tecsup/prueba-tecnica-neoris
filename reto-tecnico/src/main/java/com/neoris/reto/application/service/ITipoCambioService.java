package com.neoris.reto.application.service;

import com.neoris.reto.application.dto.response.MonedaDestinoResponse;
import com.neoris.reto.application.dto.response.MonedaOrigenResponse;
import com.neoris.reto.application.dto.request.TipoCambioMontoRequest;
import com.neoris.reto.application.dto.request.TipoCambioRequest;
import com.neoris.reto.application.dto.request.TipoCambioDTO;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.Observable;


public interface ITipoCambioService {
    Completable guardarTipoCambio(TipoCambioRequest tipoCambioRequest);
    Single<TipoCambioDTO> obtenerTipoCambioId(TipoCambioMontoRequest tipoCambioMontoRequest, Long id);
    Single<TipoCambioDTO> obtenerTipoCambio(TipoCambioMontoRequest tipoCambioMontoRequest);
    Completable actualizarTipoCambio(TipoCambioRequest tipoCambioRequest , Long id);
    Observable<MonedaOrigenResponse> obtenerMonedasOrigen();
    Observable<MonedaDestinoResponse> obtenerMonedasDestino();


}
