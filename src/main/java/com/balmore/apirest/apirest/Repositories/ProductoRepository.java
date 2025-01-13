package com.balmore.apirest.apirest.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.balmore.apirest.apirest.Entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM Producto p " +
       "JOIN FETCH p.proveedor " +
       "JOIN FETCH p.categoria")
    List<Producto> findAllProductosWithRelations();
}
