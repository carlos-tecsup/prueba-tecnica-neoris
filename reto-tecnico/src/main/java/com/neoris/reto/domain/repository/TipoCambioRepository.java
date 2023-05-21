package com.neoris.reto.domain.repository;

import com.neoris.reto.application.dto.request.TipoCambioRequest;
import com.neoris.reto.domain.TipoCambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCambioRepository extends JpaRepository<TipoCambio,Long> {
    TipoCambio findByMonedaOrigenAndMonedaDestino(String monedaOrigen , String monedaDestino);

}
