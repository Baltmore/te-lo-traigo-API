package com.balmore.apirest.apirest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.balmore.apirest.apirest.Dtos.ProveedorDTO;
import com.balmore.apirest.apirest.Entities.Proveedor;
import com.balmore.apirest.apirest.services.ProveedorService;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Proveedor>>> getAllProveedores() {
        return proveedorService.getAllSponsor().thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Proveedor>> getProveedorById(@PathVariable Long id) {
        return proveedorService.getSponsorById(id).thenApply(ResponseEntity::ok);
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Proveedor>> createProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.createSponsor(proveedor).thenApply(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Proveedor>> updateProveedor(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) throws JsonProcessingException {
        return proveedorService.updateSponsor(id, updates).thenApply(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Map<String, String>>> deleteProveedor(@PathVariable Long id) {
        return proveedorService.deleteSponsor(id);
    }

    @GetMapping("/{id}/detalle")
    public CompletableFuture<ResponseEntity<ProveedorDTO>> getProveedorConProductos(@PathVariable Long id) {
        return proveedorService.getProveedorConProductos(id).thenApply(ResponseEntity::ok);
    }
}