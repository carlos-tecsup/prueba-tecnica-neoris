package com.neoris.reto.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@Entity
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Table(name = "tb_tipo_cambio")
public class TipoCambio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "moneda_origen")
    private String monedaOrigen;

    @Column(name = "moneda_destino")
    private String monedaDestino;

    @Column(name = "tipo_cambio")
    private Double tipoCambio;

    @Column(name = "usuario_adicion")
    private String usuarioAdicion;

    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;

    @Column(name = "fecha_adicion")
    private Date fechaAdicion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;
    @PrePersist
    public void prePersist() {
        if (this.fechaAdicion == null) {
            this.fechaAdicion = new Date();
        }

        if (this.fechaModificacion == null) {
            this.fechaModificacion = new Date();
        }
    }

}
