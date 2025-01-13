package com.balmore.apirest.apirest.Entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "tipo_producto_id", nullable = false)
    private TipoProducto tipoProducto;

    @Column(nullable = false)
    private Double peso;

    @Column(name = "costo_articulo", nullable = false)
    private Double costoArticulo;

    @Column(nullable = false)
    private Double flete;

    @Column(name = "gastos_importacion", nullable = false)
    private Double gastosImportacion;

    @Column(nullable = false)
    private Double seguro;

    @Column(nullable = false)
    private Double impuestos;

    @Column(name = "costo_total_importacion", nullable = false)
    private Double costoTotalImportacion;

    @Column(name = "costo_final", nullable = false)
    private Double costoFinal;

    @Column(name = "fecha_cotizacion", nullable = false, updatable = false)
    private LocalDateTime fechaCotizacion = LocalDateTime.now();
}
