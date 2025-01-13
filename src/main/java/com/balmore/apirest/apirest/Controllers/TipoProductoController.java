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

import com.balmore.apirest.apirest.Entities.TipoProducto;
import com.balmore.apirest.apirest.services.TipoProductoService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/tipo/producto")
public class TipoProductoController {
    @Autowired
    private TipoProductoService tipoProductoService;

    @GetMapping
    public CompletableFuture<List<TipoProducto>> getAllTipoProducto() {
        return tipoProductoService.getAllTipoProducto();
    }

    @GetMapping("/{id}")
    public CompletableFuture<TipoProducto> getTipoProductoById(@PathVariable Long id) {
        return tipoProductoService.getTipoProductoById(id);
    }

    @PostMapping
    public CompletableFuture<TipoProducto> createTipoProducto(@RequestBody TipoProducto tipoProducto) {
        return tipoProductoService.createTipoProducto(tipoProducto);
    }

    @PutMapping("/{id}")
    public CompletableFuture<TipoProducto> updateTipoProducto(@PathVariable Long id, @RequestBody Map<String, Object> updates) throws JsonProcessingException {
        return tipoProductoService.updateTipoProducto(id, updates);
    }


    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Map<String, String>>> deleteTipoProducto(@PathVariable Long id) {
        return tipoProductoService.deleteTipoProducto(id);
    }
}
