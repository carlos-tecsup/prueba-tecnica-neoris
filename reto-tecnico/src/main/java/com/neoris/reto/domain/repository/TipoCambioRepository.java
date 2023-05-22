package com.neoris.reto.domain.repository;

import com.neoris.reto.application.dto.request.TipoCambioRequest;
import com.neoris.reto.domain.TipoCambio;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TipoCambioRepository extends JpaRepository<TipoCambio,Long> {
    Optional<TipoCambio> findByMonedaOrigenAndMonedaDestino(String monedaOrigen , String monedaDestino);
    List<TipoCambio> findAll();

}
