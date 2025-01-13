package com.balmore.apirest.apirest.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.balmore.apirest.apirest.Entities.TipoProducto;
import com.balmore.apirest.apirest.Repositories.TipoProductoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TipoProductoService {
    
    @Autowired
    private TipoProductoRepository tipoProductoRepository;

    @Async
    public CompletableFuture<List<TipoProducto>> getAllTipoProducto() {
        List<TipoProducto> tipoProducto = tipoProductoRepository.findAll();
        return CompletableFuture.completedFuture(tipoProducto);
    }

    @Async
    public CompletableFuture<TipoProducto> getTipoProductoById(Long id) {
        TipoProducto tipoProducto = tipoProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el tipo producto: " + id));
        return CompletableFuture.completedFuture(tipoProducto);
    }

    @Async
    public CompletableFuture<TipoProducto> createTipoProducto(TipoProducto tipoProducto) {
        TipoProducto savedTipoProducto = tipoProductoRepository.save(tipoProducto);
        return CompletableFuture.completedFuture(savedTipoProducto);
    }

    @Async
    public CompletableFuture<TipoProducto> updateTipoProducto(Long id, Map<String, Object> updates) throws JsonProcessingException {
        TipoProducto tipoProducto = tipoProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el tipo producto: " + id));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.updateValue(tipoProducto, updates);

        TipoProducto updatedTipoProducto = tipoProductoRepository.save(tipoProducto);
        return CompletableFuture.completedFuture(updatedTipoProducto);
    }

    @Async
    public CompletableFuture<ResponseEntity<Map<String, String>>> deleteTipoProducto(Long id) {
        TipoProducto tipoProducto = tipoProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el tipo producto: " + id));
    
                tipoProductoRepository.delete(tipoProducto);
    
        Map<String, String> response = new HashMap<>();
        response.put("message", "Tipo producto eliminado correctamente");
        return CompletableFuture.completedFuture(ResponseEntity.ok(response));
    }
}
