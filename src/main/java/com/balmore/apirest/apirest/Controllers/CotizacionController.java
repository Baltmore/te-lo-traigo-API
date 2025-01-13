package com.balmore.apirest.apirest.Controllers;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balmore.apirest.apirest.Dtos.cotizacionDTO;
import com.balmore.apirest.apirest.Entities.Cotizacion;
import com.balmore.apirest.apirest.services.CotizacionService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/cotizacion")
public class CotizacionController {
    @Autowired
    private CotizacionService cotizacionService;

    @GetMapping
    public CompletableFuture<List<Cotizacion>> getAllCotizacion() {
        return cotizacionService.getAllCotizacion();
    }

    @GetMapping("/{id}")
    public CompletableFuture<Cotizacion> getCotizacionById(@PathVariable Long id) {
        return cotizacionService.getCotizacionById(id);
    }

    @PostMapping
    public CompletableFuture<Cotizacion> createCotizacion(@RequestBody Cotizacion cotizacion) {
        return cotizacionService.createCotizacion(cotizacion);
    }

    @PutMapping("/{id}")
    public CompletableFuture<Cotizacion> updateCotizacion(@PathVariable Long id,
            @RequestBody Map<String, Object> updates) throws JsonProcessingException {
        return cotizacionService.updateCotizacion(id, updates);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Map<String, String>>> deleteCotizaciona(@PathVariable Long id) {
        return cotizacionService.deleteCotizacion(id);
    }

    /*
     * @GetMapping("/detalle")
     * public CompletableFuture<List<cotizacionDTO>> obtenerProductosConRelaciones()
     * {
     * return cotizacionService.obtenerTodasLasCotizacionesConRelaciones();
     * }
     */

    @GetMapping("/detalle")
    public ResponseEntity<List<cotizacionDTO>> obtenerProductosConRelaciones() {
        // Llama al servicio y espera a que se complete la tarea asincrónica con join()
        List<cotizacionDTO> cotizaciones = cotizacionService.obtenerTodasLasCotizacionesConRelaciones().join();

        // Devuelve la lista de cotizaciones en la respuesta HTTP
        return ResponseEntity.ok(cotizaciones);
    }
    /*
     * @GetMapping
     * public ResponseEntity<List<Cliente>> getAllCliente() {
     * List<Cliente> clientes = clienteService.getAllCliente().join();
     * return ResponseEntity.ok(clientes);
     * }
     */
}
