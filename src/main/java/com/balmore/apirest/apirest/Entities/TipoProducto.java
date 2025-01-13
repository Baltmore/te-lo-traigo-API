package com.balmore.apirest.apirest.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class TipoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(name = "porcentaje_flete", nullable = false)
    private Double porcentajeFlete;

    @Column(name = "porcentaje_gastos_importacion", nullable = false)
    private Double porcentajeGastosImportacion;

    @Column(name = "porcentaje_seguro", nullable = false)
    private Double porcentajeSeguro;

    @Column(name = "porcentaje_impuestos", nullable = false)
    private Double porcentajeImpuestos;

    @OneToMany(mappedBy = "tipoProducto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Cotizacion> cotizaciones;

    public TipoProducto() {
    }

    public TipoProducto(String nombre, Double porcentajeFlete, Double porcentajeGastosImportacion,
            Double porcentajeSeguro, Double porcentajeImpuestos) {
        this.nombre = nombre;
        this.porcentajeFlete = porcentajeFlete;
        this.porcentajeGastosImportacion = porcentajeGastosImportacion;
        this.porcentajeSeguro = porcentajeSeguro;
        this.porcentajeImpuestos = porcentajeImpuestos;
    }
}
