package com.balmore.apirest.apirest.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.balmore.apirest.apirest.Entities.Cliente;
import com.balmore.apirest.apirest.Repositories.ClienteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Async
    public CompletableFuture<List<Cliente>> getAllCliente() {
        List<Cliente> cliente = clienteRepository.findAll();
        return CompletableFuture.completedFuture(cliente);
    }

    @Async
    public CompletableFuture<Cliente> getClienteById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el cliente: " + id));
        return CompletableFuture.completedFuture(cliente);
    }

    @Async
    public CompletableFuture<Cliente> createCliente(Cliente cliente) {
        Cliente savedCliente = clienteRepository.save(cliente);
        return CompletableFuture.completedFuture(savedCliente);
    }

    @Async
    public CompletableFuture<Cliente> updateCliente(Long id, Map<String, Object> updates) throws JsonProcessingException {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el cliente: " + id));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.updateValue(cliente, updates);

        Cliente updatedCliente = clienteRepository.save(cliente);
        return CompletableFuture.completedFuture(updatedCliente);
    }

    @Async
    public CompletableFuture<ResponseEntity<Map<String, String>>> deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el cliente: " + id));
    
                clienteRepository.delete(cliente);
    
        Map<String, String> response = new HashMap<>();
        response.put("message", "Cliente eliminado correctamente");
        return CompletableFuture.completedFuture(ResponseEntity.ok(response));
    }
}
