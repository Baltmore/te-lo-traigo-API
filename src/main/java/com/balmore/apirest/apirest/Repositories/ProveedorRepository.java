package com.balmore.apirest.apirest.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balmore.apirest.apirest.Entities.Producto;
import com.balmore.apirest.apirest.Entities.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
        @Query("SELECT p FROM Producto p " +
            "JOIN FETCH p.categoria c " +
            "JOIN FETCH p.proveedor pr " +
            "WHERE pr.id = :idProveedor")
    List<Producto> findProductosByProveedor(@Param("idProveedor") Long idProveedor);
}
