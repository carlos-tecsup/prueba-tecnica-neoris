package com.neoris.reto.infraestructure.controller;

import com.neoris.reto.application.dto.request.TipoCambioMontoRequest;
import com.neoris.reto.application.dto.request.TipoCambioRequest;
import com.neoris.reto.application.dto.request.TipoCambioDto;
import com.neoris.reto.application.dto.request.TipoCambioResponse;
import com.neoris.reto.application.dto.response.MensajeResponse;
import com.neoris.reto.application.service.ITipoCambioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/tipo-cambio")
public  class TipoCambioController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    ITipoCambioService tipoCambioService;

    @PostMapping("/registrar")
    public ResponseEntity<MensajeResponse> crearTipoCambio(@Valid @RequestBody TipoCambioRequest tipoCambioRequest) {
        tipoCambioService.guardarTipoCambio(tipoCambioRequest);
        MensajeResponse mensajeResponse = new MensajeResponse("Tipo de cambio Creado");

        return new ResponseEntity<>(mensajeResponse, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TipoCambioResponse obtenerTipoCambio(
            @RequestBody TipoCambioMontoRequest request) {

        TipoCambioDto tipoCambioDto = tipoCambioService.obtenerTipoCambio(request);
        TipoCambioResponse response = modelMapper.map(tipoCambioDto, TipoCambioResponse.class);
        return response;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TipoCambioResponse obtenerTipoCambioId(
            @RequestBody TipoCambioMontoRequest request ,@PathVariable Long id) {

        TipoCambioDto tipoCambioDto = tipoCambioService.obtenerTipoCambioId(request, id);
        TipoCambioResponse response = modelMapper.map(tipoCambioDto, TipoCambioResponse.class);
        return response;
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MensajeResponse> actualizarTipoCambio(
            @RequestBody TipoCambioRequest request , @PathVariable Long id) {

        tipoCambioService.actualizarTipoCambio(request, id);
        MensajeResponse mensajeResponse = new MensajeResponse("Tipo de cambio actualizado");

        return new ResponseEntity<>(mensajeResponse, HttpStatus.OK);
    }
}