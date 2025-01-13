package com.balmore.apirest.apirest.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.balmore.apirest.apirest.Entities.Categoria;
import com.balmore.apirest.apirest.Repositories.CategoriaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoryRepository;

    @Async
    public CompletableFuture<List<Categoria>> getAllCategory() {
        List<Categoria> categorias = categoryRepository.findAll();
        return CompletableFuture.completedFuture(categorias);
    }

    @Async
    public CompletableFuture<Categoria> getCategoryById(Long id) {
        Categoria categoria = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el categoria: " + id));
        return CompletableFuture.completedFuture(categoria);
    }

    @Async
    public CompletableFuture<Categoria> createCategory(Categoria categoria) {
        Categoria savedCategoria = categoryRepository.save(categoria);
        return CompletableFuture.completedFuture(savedCategoria);
    }

    @Async
    public CompletableFuture<Categoria> updateCategory(Long id, Map<String, Object> updates) throws JsonProcessingException {
        Categoria categoria = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el categoria: " + id));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.updateValue(categoria, updates);

        Categoria updatedCategoria = categoryRepository.save(categoria);
        return CompletableFuture.completedFuture(updatedCategoria);
    }

    @Async
    public CompletableFuture<ResponseEntity<Map<String, String>>> deleteCategory(Long id) {
        Categoria categoria = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el categoria: " + id));
    
                categoryRepository.delete(categoria);
    
        Map<String, String> response = new HashMap<>();
        response.put("message", "Categoria eliminado correctamente");
        return CompletableFuture.completedFuture(ResponseEntity.ok(response));
    }
}
