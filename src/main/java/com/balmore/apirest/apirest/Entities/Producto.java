package com.balmore.apirest.apirest.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

// se le indica que es una entidad
@Entity
//se setean setters y getters
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProveedor", nullable = false) // Relación con Proveedor
    @JsonIgnore
    private Proveedor proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategoria", nullable = false) // Relación con Categoria
    @JsonIgnore
    private Categoria categoria;
}
