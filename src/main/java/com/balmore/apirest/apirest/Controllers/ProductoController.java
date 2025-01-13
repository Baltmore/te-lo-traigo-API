package com.balmore.apirest.apirest.Controllers;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balmore.apirest.apirest.services.ProductoService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.balmore.apirest.apirest.Dtos.ProductoDTO;
import com.balmore.apirest.apirest.Entities.Producto;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Producto>>> getAllProducto() {
        return productoService.getAllProduct()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Producto>> getProductoById(@PathVariable Long id) {
        return productoService.getProductById(id)
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Producto>> createProducto(@RequestBody Producto producto) {
        return productoService.createProduct(producto)
                .thenApply(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Producto>> updateProducto(@PathVariable Long id, @RequestBody Map<String, Object> updates)
            throws JsonProcessingException {
        return productoService.updateProduct(id, updates)
                .thenApply(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Map<String, String>>> deleteProducto(@PathVariable Long id) {
        return productoService.deleteProduct(id);
    }

    @GetMapping("/detalle")
    public CompletableFuture<List<ProductoDTO>> obtenerProductosConRelaciones() {
        return productoService.obtenerTodosLosProductosConRelaciones();
    }
}
