package com.neoris.reto.application.service.impl;

import com.neoris.reto.application.dto.response.MonedaDestinoResponse;
import com.neoris.reto.application.dto.response.MonedaOrigenResponse;
import com.neoris.reto.application.dto.request.TipoCambioMontoRequest;
import com.neoris.reto.application.dto.request.TipoCambioRequest;
import com.neoris.reto.application.dto.request.TipoCambioDTO;
import com.neoris.reto.application.exceptions.TipoCambioNotFoundException;
import com.neoris.reto.application.mapper.ITipoCambioMapper;
import com.neoris.reto.application.service.ITipoCambioService;
import com.neoris.reto.domain.TipoCambio;
import com.neoris.reto.domain.repository.TipoCambioRepository;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class TipoCambioServiceImpl implements ITipoCambioService {
    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    @Autowired
    private ITipoCambioMapper tipoCambioMapper;


    @Override
    public Completable guardarTipoCambio(TipoCambioRequest tipoCambioRequest) {
        TipoCambio tipoCambio = tipoCambioMapper.toModel(tipoCambioRequest);
        return Completable.fromAction(() -> tipoCambioRepository.save(tipoCambio));
    }



    @Override
    public Single<TipoCambioDTO> obtenerTipoCambioId(TipoCambioMontoRequest tipoCambioMontoRequest, Long id) {
        return Single.fromCallable(() -> {
            TipoCambio tipoCambio = tipoCambioRepository.findById(id)
                    .orElseThrow(() -> new TipoCambioNotFoundException("No se encontró un tipo de cambio para las monedas especificadas."));

            String monedaOrigen = tipoCambioMontoRequest.getMonedaOrigen();
            String monedaDestino = tipoCambioMontoRequest.getMonedaDestino();

            TipoCambioDTO tipoCambioDto = new TipoCambioDTO();
            tipoCambioDto.setTipoCambio(tipoCambioMontoRequest.getMonto());

            if (tipoCambio.getMonedaOrigen().equalsIgnoreCase(monedaOrigen)
                    && tipoCambio.getMonedaDestino().equalsIgnoreCase(monedaDestino)) {
                tipoCambioDto.setMontoTipoCambio(tipoCambioMontoRequest.getMonto() * tipoCambio.getTipoCambio());
                tipoCambioDto.setTipoCambio(tipoCambio.getTipoCambio());
            }

            tipoCambioDto.setMonedaOrigen(tipoCambioMontoRequest.getMonedaOrigen());
            tipoCambioDto.setMonedaDestino(tipoCambioMontoRequest.getMonedaDestino());

            return tipoCambioDto;
        });
    }
    @Override
    public Single<TipoCambioDTO> obtenerTipoCambio(TipoCambioMontoRequest tipoCambioMontoRequest) {
        return Single.fromCallable(() -> {
            TipoCambio tipoCambio = tipoCambioRepository.findByMonedaOrigenAndMonedaDestino(
                            tipoCambioMontoRequest.getMonedaOrigen(),
                            tipoCambioMontoRequest.getMonedaDestino())
                    .orElseThrow(() -> new TipoCambioNotFoundException("No se encontró un tipo de cambio para las monedas especificadas."));

            String monedaOrigen = tipoCambioMontoRequest.getMonedaOrigen();
            String monedaDestino = tipoCambioMontoRequest.getMonedaDestino();

            TipoCambioDTO tipoCambioDto = new TipoCambioDTO();
            tipoCambioDto.setTipoCambio(tipoCambioMontoRequest.getMonto());

            if (tipoCambio.getMonedaOrigen().equalsIgnoreCase(monedaOrigen)
                    && tipoCambio.getMonedaDestino().equalsIgnoreCase(monedaDestino)) {
                tipoCambioDto.setMontoTipoCambio(tipoCambioMontoRequest.getMonto() * tipoCambio.getTipoCambio());
                tipoCambioDto.setTipoCambio(tipoCambio.getTipoCambio());
            }

            tipoCambioDto.setMonto(tipoCambioMontoRequest.getMonto());
            tipoCambioDto.setMonedaOrigen(tipoCambioMontoRequest.getMonedaOrigen());
            tipoCambioDto.setMonedaDestino(tipoCambioMontoRequest.getMonedaDestino());

            return tipoCambioDto;
        });
    }
    @Override
    public Completable actualizarTipoCambio(TipoCambioRequest tipoCambioRequest, Long id) {
        return Completable.fromAction(() -> {
            TipoCambio tipoCambio = tipoCambioRepository
                    .findById(id)
                    .orElseThrow(() -> new TipoCambioNotFoundException("No se encontró un tipo de cambio para las monedas especificadas."));

            tipoCambio.setMonedaOrigen(tipoCambioRequest.getMonedaOrigen());
            tipoCambio.setMonedaDestino(tipoCambioRequest.getMonedaDestino());
            if (Objects.nonNull(tipoCambioRequest.getTipoCambio())) {
                tipoCambio.setTipoCambio(tipoCambioRequest.getTipoCambio());
            }
            tipoCambio.setUsuarioModificacion(tipoCambioRequest.getUsuarioModificacion());
            tipoCambio.setFechaModificacion(new Date());

            tipoCambioRepository.save(tipoCambio);
        });
    }



    @Override
    public Observable<MonedaOrigenResponse> obtenerMonedasOrigen() {
        return Observable.fromIterable(tipoCambioRepository.findAll())
                .map(tipoCambio -> new MonedaOrigenResponse(tipoCambio.getMonedaOrigen())).distinct();
    }

    @Override
    public Observable<MonedaDestinoResponse> obtenerMonedasDestino() {
        return Observable.fromIterable(tipoCambioRepository.findAll())
                .map(tipoCambio -> new MonedaDestinoResponse(tipoCambio.getMonedaDestino())).distinct();
    }


}
