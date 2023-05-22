package com.neoris.reto.infraestructure.controller;

import com.neoris.reto.application.dto.request.*;
import com.neoris.reto.application.dto.response.MensajeResponse;
import com.neoris.reto.application.dto.response.MonedaDestinoResponse;
import com.neoris.reto.application.dto.response.MonedaOrigenResponse;
import com.neoris.reto.application.service.ITipoCambioService;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/tipo-cambio")
public  class TipoCambioController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    ITipoCambioService tipoCambioService;

    @PostMapping("/registrar")
    public Single<ResponseEntity<MensajeResponse>> crearTipoCambio(@Valid @RequestBody TipoCambioRequest tipoCambioRequest) {
        return tipoCambioService.guardarTipoCambio(tipoCambioRequest)
                .toSingleDefault(new ResponseEntity<>(new MensajeResponse("Tipo de cambio Creado"), HttpStatus.CREATED));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<TipoCambioResponse> obtenerTipoCambio(@RequestBody TipoCambioMontoRequest request) {
        return tipoCambioService.obtenerTipoCambio(request)
                .map(tipoCambioDto -> modelMapper.map(tipoCambioDto, TipoCambioResponse.class));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<TipoCambioResponse> obtenerTipoCambioId(@RequestBody TipoCambioMontoRequest request, @PathVariable Long id) {
        return tipoCambioService.obtenerTipoCambioId(request, id)
                .map(tipoCambioDto -> modelMapper.map(tipoCambioDto, TipoCambioResponse.class));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<MensajeResponse>> actualizarTipoCambio(@RequestBody TipoCambioRequest request, @PathVariable Long id) {
        return tipoCambioService.actualizarTipoCambio(request, id)
                .andThen(Single.just(new ResponseEntity<>(new MensajeResponse("Tipo de cambio actualizado"), HttpStatus.OK)));
    }

    @GetMapping(value = "/monedas-origen", produces = MediaType.APPLICATION_JSON_VALUE)
    public Observable<MonedaOrigenResponse> obtenerMonedasOrigen() {
        return tipoCambioService.obtenerMonedasOrigen();
    }

    @GetMapping(value = "/monedas-destino", produces = MediaType.APPLICATION_JSON_VALUE)
    public Observable<MonedaDestinoResponse> obtenerMonedasDestino() {
        return tipoCambioService.obtenerMonedasDestino();
    }
}