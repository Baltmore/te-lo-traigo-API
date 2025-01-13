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

import com.balmore.apirest.apirest.Entities.Categoria;
import com.balmore.apirest.apirest.services.CategoriaService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public CompletableFuture<List<Categoria>> getAllCategorias() {
        return categoriaService.getAllCategory();
    }

    @GetMapping("/{id}")
    public CompletableFuture<Categoria> getCategoriaById(@PathVariable Long id) {
        return categoriaService.getCategoryById(id);
    }

    @PostMapping
    public CompletableFuture<Categoria> createCategoria(@RequestBody Categoria categoria) {
        return categoriaService.createCategory(categoria);
    }

    @PutMapping("/{id}")
    public CompletableFuture<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Map<String, Object> updates) throws JsonProcessingException {
        return categoriaService.updateCategory(id, updates);
    }


    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Map<String, String>>> deleteCategoria(@PathVariable Long id) {
        return categoriaService.deleteCategory(id);
    }
}
