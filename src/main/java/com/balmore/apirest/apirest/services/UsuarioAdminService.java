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
import com.balmore.apirest.apirest.Entities.UsuarioAdmin;
import com.balmore.apirest.apirest.Repositories.TipoProductoRepository;
import com.balmore.apirest.apirest.Repositories.UsuarioAdminRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UsuarioAdminService {
    
    @Autowired
    private UsuarioAdminRepository usuarioAdminRepository;
    @Autowired
    private TipoProductoRepository tipoProductoRepository;

    @Async
    public CompletableFuture<List<UsuarioAdmin>> getAllUsuarioAdmin() {
        List<UsuarioAdmin> usuarioAdmin = usuarioAdminRepository.findAll();
        return CompletableFuture.completedFuture(usuarioAdmin);
    }

    @Async
    public CompletableFuture<UsuarioAdmin> getUsuarioAdminById(Long id) {
        UsuarioAdmin usuarioAdmin = usuarioAdminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario admin: " + id));
        return CompletableFuture.completedFuture(usuarioAdmin);
    }

    @Async
    public CompletableFuture<UsuarioAdmin> createUsuarioAdmin(UsuarioAdmin usuarioAdmin) {
        UsuarioAdmin savedUsuarioAdmin = usuarioAdminRepository.save(usuarioAdmin);
        return CompletableFuture.completedFuture(savedUsuarioAdmin);
    }

    @Async
    public CompletableFuture<UsuarioAdmin> updateUsuarioAdmin(Long id, Map<String, Object> updates) throws JsonProcessingException {
        UsuarioAdmin usuarioAdmin = usuarioAdminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario admin: " + id));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.updateValue(usuarioAdmin, updates);

        UsuarioAdmin updatedUsuarioAdmin = usuarioAdminRepository.save(usuarioAdmin);
        return CompletableFuture.completedFuture(updatedUsuarioAdmin);
    }

    @Async
    public CompletableFuture<ResponseEntity<Map<String, String>>> deleteUsuarioAdmin(Long id) {
        UsuarioAdmin usuarioAdmin = usuarioAdminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario admin: " + id));
    
                usuarioAdminRepository.delete(usuarioAdmin);
    
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario admin eliminado correctamente");
        return CompletableFuture.completedFuture(ResponseEntity.ok(response));
    }

        public void insertarDatosIniciales() {
        // Verificar si los datos de `TipoProducto` ya existen
        if (tipoProductoRepository.count() == 0) {
            tipoProductoRepository.saveAll(List.of(
                new TipoProducto("Computadoras", 1.00, 2.00, 1.00, 13.00),
                new TipoProducto("Ropa", 1.00, 5.00, 2.00, 15.00),
                new TipoProducto("Repuestos para vehículos", 6.00, 4.00, 10.00, 13.00)
            ));
        }

        // Verificar si el usuario administrador ya existe
        if (!usuarioAdminRepository.existsByNombreUsuario("admin")) {
            UsuarioAdmin admin = new UsuarioAdmin();
            admin.setNombreUsuario("admin");
            admin.setContrasena("root");
            admin.setRol("ADMIN");
            usuarioAdminRepository.save(admin);
        }
    }
}
