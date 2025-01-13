package com.balmore.apirest.apirest.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.balmore.apirest.apirest.Dtos.ProductoDTO;
import com.balmore.apirest.apirest.Entities.Producto;
import com.balmore.apirest.apirest.Repositories.ProductoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

      @Async
    public CompletableFuture<List<Producto>> getAllProduct() {
        List<Producto> productos = productoRepository.findAll();
        return CompletableFuture.completedFuture(productos);
    }

    @Async
    public CompletableFuture<Producto> getProductById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto: " + id));
        return CompletableFuture.completedFuture(producto);
    }

    @Async
    public CompletableFuture<Producto> createProduct(Producto producto) {
        Producto nuevoProducto = productoRepository.save(producto);
        return CompletableFuture.completedFuture(nuevoProducto);
    }

    @Async
    public CompletableFuture<Producto> updateProduct(Long id, Map<String, Object> updates) throws JsonProcessingException {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto: " + id));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.updateValue(producto, updates);

        Producto productoActualizado = productoRepository.save(producto);
        return CompletableFuture.completedFuture(productoActualizado);
    }

    @Async
    public CompletableFuture<ResponseEntity<Map<String, String>>> deleteProduct(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto: " + id));
    
        productoRepository.delete(producto);
    
        Map<String, String> response = new HashMap<>();
        response.put("message", "Producto eliminado correctamente");
        return CompletableFuture.completedFuture(ResponseEntity.ok(response));
    }

    @Async
    public CompletableFuture<List<ProductoDTO>> obtenerTodosLosProductosConRelaciones() {
        List<ProductoDTO> productosDTO = productoRepository.findAllProductosWithRelations()
                .stream()
                .map(producto -> {
                    ProductoDTO dto = new ProductoDTO();
                    dto.setId(producto.getId());
                    dto.setNombre(producto.getNombre());
                    dto.setPrecio(producto.getPrecio());

                    // Mapear proveedor
                    ProductoDTO.ProveedorDTO proveedorDTO = new ProductoDTO.ProveedorDTO();
                    proveedorDTO.setId(producto.getProveedor().getId());
                    proveedorDTO.setNombre(producto.getProveedor().getNombre());
                    proveedorDTO.setDireccion(producto.getProveedor().getDireccion());
                    proveedorDTO.setTelefono(producto.getProveedor().getTelefono());
                    dto.setProveedores(proveedorDTO);

                    // Mapear categoría
                    if (producto.getCategoria() != null) {
                        ProductoDTO.CategoriaDTO categoriaDTO = new ProductoDTO.CategoriaDTO();
                        categoriaDTO.setId(producto.getCategoria().getId());
                        categoriaDTO.setNombre(producto.getCategoria().getNombre());
                        categoriaDTO.setDescripcion(producto.getCategoria().getDescripcion());
                        dto.setCategorias(categoriaDTO);
                    }

                    return dto;
                })
                .collect(Collectors.toList());

        return CompletableFuture.completedFuture(productosDTO);
    }

}
