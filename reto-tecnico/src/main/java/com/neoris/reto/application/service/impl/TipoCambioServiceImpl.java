package com.neoris.reto.application.service.impl;

import com.neoris.reto.application.dto.request.TipoCambioMontoRequest;
import com.neoris.reto.application.dto.request.TipoCambioRequest;
import com.neoris.reto.application.dto.request.TipoCambioDto;
import com.neoris.reto.application.exceptions.TipoCambioNotFoundException;
import com.neoris.reto.application.mapper.ITipoCambioMapper;
import com.neoris.reto.application.service.ITipoCambioService;
import com.neoris.reto.domain.TipoCambio;
import com.neoris.reto.domain.repository.TipoCambioRepository;
import com.sun.org.apache.regexp.internal.RE;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    public void guardarTipoCambio(TipoCambioRequest tipoCambioRequest) {

        TipoCambio tipoCambio = tipoCambioMapper.toModel(tipoCambioRequest);

        tipoCambioRepository.save(tipoCambio);
    }

    @Override
    public TipoCambioDto obtenerTipoCambioId(TipoCambioMontoRequest tipoCambioMontoRequest, Long id) {
        TipoCambio tipoCambio = tipoCambioRepository.findById(id)
                .orElseThrow(() -> new TipoCambioNotFoundException("No se encontró un tipo de cambio para las monedas especificadas."));

        String monedaOrigen = tipoCambioMontoRequest.getMonedaOrigen();
            String monedaDestino = tipoCambioMontoRequest.getMonedaDestino();

            TipoCambioDto tipoCambioDto = new TipoCambioDto();
            tipoCambioDto.setTipoCambio(tipoCambioMontoRequest.getMonto());

                if (tipoCambio.getMonedaOrigen().equalsIgnoreCase(monedaOrigen)
                        && tipoCambio.getMonedaDestino().equalsIgnoreCase(monedaDestino)) {
                    // Calculo del monto con el tipo de cambio.
                    tipoCambioDto.setMontoTipoCambio(tipoCambioMontoRequest.getMonto() * tipoCambio.getTipoCambio());
                    tipoCambioDto.setTipoCambio(tipoCambio.getTipoCambio());
                }

                tipoCambioDto.setMonedaOrigen(tipoCambioMontoRequest.getMonedaOrigen());
                tipoCambioDto.setMonedaDestino(tipoCambioMontoRequest.getMonedaDestino());
                return tipoCambioDto;

    }

    @Override
    public TipoCambioDto obtenerTipoCambio(TipoCambioMontoRequest tipoCambioMontoRequest) {
        TipoCambio tipoCambio = tipoCambioRepository.findByMonedaOrigenAndMonedaDestino(
                tipoCambioMontoRequest.getMonedaOrigen(),
                tipoCambioMontoRequest.getMonedaDestino());

        String monedaOrigen = tipoCambioMontoRequest.getMonedaOrigen();
        String monedaDestino = tipoCambioMontoRequest.getMonedaDestino();

        TipoCambioDto tipoCambioDto = new TipoCambioDto();
        tipoCambioDto.setTipoCambio(tipoCambioMontoRequest.getMonto());

        if (tipoCambio.getMonedaOrigen().equalsIgnoreCase(monedaOrigen)
                && tipoCambio.getMonedaDestino().equalsIgnoreCase(monedaDestino)) {
            // Calculo del monto con el tipo de cambio.
            tipoCambioDto.setMontoTipoCambio(tipoCambioMontoRequest.getMonto() * tipoCambio.getTipoCambio());
            tipoCambioDto.setTipoCambio(tipoCambio.getTipoCambio());
        }

        tipoCambioDto.setMonedaOrigen(tipoCambioMontoRequest.getMonedaOrigen());
        tipoCambioDto.setMonedaDestino(tipoCambioMontoRequest.getMonedaDestino());

        return tipoCambioDto;
    }

    @Override
    public void actualizarTipoCambio(TipoCambioRequest tipoCambioRequest, Long id) {

        TipoCambio tipoCambio = tipoCambioRepository
                .findById(id)
                .orElseThrow(() -> new TipoCambioNotFoundException("No se encontró un tipo de cambio para las monedas especificadas."));

        tipoCambio.setMonedaOrigen(tipoCambioRequest.getMonedaOrigen());
        tipoCambio.setMonedaDestino(tipoCambioRequest.getMonedaDestino());
        if(Objects.nonNull(tipoCambioRequest.getTipoCambio())) {
            tipoCambio.setTipoCambio(tipoCambioRequest.getTipoCambio());
        }
        tipoCambio.setUsuarioModificacion(tipoCambioRequest.getUsuarioModificacion());
        tipoCambio.setFechaModificacion(new Date());

        tipoCambioRepository.save(tipoCambio);

    }

}
