package com.balmore.apirest.apirest.Dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class cotizacionDTO {
    private Long id;
    private Double costoArticulo;
    private Double costoFinal;
    private Double costoTotalImportacion;
    private Double flete;
    private Double gastosImportacion;
    private Double impuestos;
    private Double peso;
    private Double seguro;
    private LocalDateTime fechaCotizacion;
    private ClienteDTO cliente;
    private TipoProductoDTO tipoProducto;

    @Data
    public static class ClienteDTO {
        private Long id;
        private String nombre;
        private String correo;
    }

    @Data
    public static class TipoProductoDTO {
        private Long id;
        private String nombre;
        private Double porcentajeFlete;
        private Double porcentajeGastosImportacion;
        private Double porcentajeSeguro;
        private Double porcentajeImpuestos;
    }
}
