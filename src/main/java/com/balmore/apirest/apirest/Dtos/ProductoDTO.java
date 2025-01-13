package com.balmore.apirest.apirest.Dtos;

import lombok.Data;

@Data
public class ProductoDTO {

    private Long id;
    private String nombre;
    private Double precio;
    private ProveedorDTO proveedores;
    private CategoriaDTO categorias;

    @Data
    public static class ProveedorDTO {
        private Long id;
        private String nombre;
        private String direccion;
        private String telefono;
    }

    @Data
    public static class CategoriaDTO {
        private Long id;
        private String nombre;
        private String descripcion;
    }

}
