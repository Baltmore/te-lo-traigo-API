package com.balmore.apirest.apirest.Dtos;

import java.util.List;

import lombok.Data;

@Data
public class ProveedorDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private List<ProductoDTO> productos;

    @Data
    public static class ProductoDTO {
        private Long id;
        private String nombre;
        private Double precio;
        private CategoriaDTO categoria;

        @Data
        public static class CategoriaDTO {
            private Long id;
            private String nombre;
            private String descripcion;
        }
    }
}
