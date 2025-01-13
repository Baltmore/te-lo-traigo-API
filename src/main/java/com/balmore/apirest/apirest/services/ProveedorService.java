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

import com.balmore.apirest.apirest.Dtos.ProveedorDTO;
import com.balmore.apirest.apirest.Entities.Producto;
import com.balmore.apirest.apirest.Entities.Proveedor;
import com.balmore.apirest.apirest.Repositories.ProveedorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

      @Async
    public CompletableFuture<List<Proveedor>> getAllSponsor() {
        List<Proveedor> proveedores = proveedorRepository.findAll();
        return CompletableFuture.completedFuture(proveedores);
    }

    @Async
    public CompletableFuture<Proveedor> getSponsorById(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el proveedor: " + id));
        return CompletableFuture.completedFuture(proveedor);
    }

    @Async
    public CompletableFuture<Proveedor> createSponsor(Proveedor proveedor) {
        Proveedor nuevoProveedor = proveedorRepository.save(proveedor);
        return CompletableFuture.completedFuture(nuevoProveedor);
    }

    @Async
    public CompletableFuture<Proveedor> updateSponsor(Long id, Map<String, Object> updates) throws JsonProcessingException {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el proveedor: " + id));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.updateValue(proveedor, updates);

        Proveedor proveedorActualizado = proveedorRepository.save(proveedor);
        return CompletableFuture.completedFuture(proveedorActualizado);
    }

        @Async
    public CompletableFuture<ResponseEntity<Map<String, String>>> deleteSponsor(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el proveedor: " + id));
    
                proveedorRepository.delete(proveedor);
    
        Map<String, String> response = new HashMap<>();
        response.put("message", "Proveedor eliminado correctamente");
        return CompletableFuture.completedFuture(ResponseEntity.ok(response));
    }

    @Transactional
    @Async
    public CompletableFuture<ProveedorDTO> getProveedorConProductos(Long idProveedor) {
        Proveedor proveedor = proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new RuntimeException("No se encontró el proveedor con ID: " + idProveedor));

        List<Producto> productos = proveedor.getProductos(); // Suponiendo que la entidad `Proveedor` tiene una relación `List<Producto>`.

        ProveedorDTO proveedorDTO = new ProveedorDTO();
        proveedorDTO.setId(proveedor.getId());
        proveedorDTO.setNombre(proveedor.getNombre());
        proveedorDTO.setDireccion(proveedor.getDireccion());
        proveedorDTO.setTelefono(proveedor.getTelefono());

        proveedorDTO.setProductos(productos.stream().map(producto -> {
            ProveedorDTO.ProductoDTO productoDTO = new ProveedorDTO.ProductoDTO();
            productoDTO.setId(producto.getId());
            productoDTO.setNombre(producto.getNombre());
            productoDTO.setPrecio(producto.getPrecio());

            if (producto.getCategoria() != null) {
                ProveedorDTO.ProductoDTO.CategoriaDTO categoriaDTO = new ProveedorDTO.ProductoDTO.CategoriaDTO();
                categoriaDTO.setId(producto.getCategoria().getId());
                categoriaDTO.setNombre(producto.getCategoria().getNombre());
                categoriaDTO.setDescripcion(producto.getCategoria().getDescripcion());
                productoDTO.setCategoria(categoriaDTO);
            }

            return productoDTO;
        }).collect(Collectors.toList()));

        return CompletableFuture.completedFuture(proveedorDTO);
    }
 
}