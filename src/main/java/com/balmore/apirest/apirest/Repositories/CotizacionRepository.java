package com.balmore.apirest.apirest.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.balmore.apirest.apirest.Entities.Cotizacion;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Long> {
    @Query("SELECT c FROM Cotizacion c " +
           "JOIN FETCH c.cliente cl " +
           "JOIN FETCH c.tipoProducto tp")
    List<Cotizacion> findCotizacionesWithRelations();
}
