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

import com.balmore.apirest.apirest.Entities.Cliente;
import com.balmore.apirest.apirest.services.ClienteService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public CompletableFuture<List<Cliente>> getAllCliente() {
        return clienteService.getAllCliente();

    }

    @GetMapping("/{id}")
    public CompletableFuture<Cliente> getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id);
    }

    @PostMapping
    public CompletableFuture<Cliente> createCliente(@RequestBody Cliente cliente) {
        return clienteService.createCliente(cliente);
    }

    @PutMapping("/{id}")
    public CompletableFuture<Cliente> updateCliente(@PathVariable Long id, @RequestBody Map<String, Object> updates)
            throws JsonProcessingException {
        return clienteService.updateCliente(id, updates);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Map<String, String>>> deleteCliente(@PathVariable Long id) {
        return clienteService.deleteCliente(id);
    }
}
