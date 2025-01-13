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

import com.balmore.apirest.apirest.Entities.UsuarioAdmin;
import com.balmore.apirest.apirest.services.UsuarioAdminService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/usuario/admin")
public class UsuarioAdminController {
        @Autowired
    private UsuarioAdminService usuarioAdminService;

    @GetMapping
    public CompletableFuture<List<UsuarioAdmin>> getAllUsuarioAdmin() {
        return usuarioAdminService.getAllUsuarioAdmin();
    }

    @GetMapping("/{id}")
    public CompletableFuture<UsuarioAdmin> getUsuarioAdminById(@PathVariable Long id) {
        return usuarioAdminService.getUsuarioAdminById(id);
    }

    @PostMapping
    public CompletableFuture<UsuarioAdmin> createUsuarioAdmin(@RequestBody UsuarioAdmin usuarioAdmin) {
        return usuarioAdminService.createUsuarioAdmin(usuarioAdmin);
    }

    @PutMapping("/{id}")
    public CompletableFuture<UsuarioAdmin> updateUsuarioAdmin(@PathVariable Long id, @RequestBody Map<String, Object> updates) throws JsonProcessingException {
        return usuarioAdminService.updateUsuarioAdmin(id, updates);
    }


    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Map<String, String>>> deleteUsuarioAdmin(@PathVariable Long id) {
        return usuarioAdminService.deleteUsuarioAdmin(id);
    }

    @GetMapping("/inicializar/datos")
    public ResponseEntity<Map<String, String>> inicializarDatos() {
        usuarioAdminService.insertarDatosIniciales();
        Map<String, String> response = Map.of("message", "Datos iniciales insertados correctamente");
        return ResponseEntity.ok(response);
    }
}
