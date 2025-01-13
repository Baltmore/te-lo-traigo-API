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

import com.balmore.apirest.apirest.Dtos.cotizacionDTO;
import com.balmore.apirest.apirest.Entities.Cotizacion;
import com.balmore.apirest.apirest.Repositories.CotizacionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class CotizacionService {
    @Autowired
    private CotizacionRepository cotizacionRepository;

    @Async
    public CompletableFuture<List<Cotizacion>> getAllCotizacion() {
        List<Cotizacion> cotizacion = cotizacionRepository.findAll();
        return CompletableFuture.completedFuture(cotizacion);
    }

    @Async
    public CompletableFuture<Cotizacion> getCotizacionById(Long id) {
        Cotizacion cotizacion = cotizacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la categoria: " + id));
        return CompletableFuture.completedFuture(cotizacion);
    }

    @Async
    public CompletableFuture<Cotizacion> createCotizacion(Cotizacion cotizacion) {
        Cotizacion savedCotizacion = cotizacionRepository.save(cotizacion);
        return CompletableFuture.completedFuture(savedCotizacion);
    }

    @Async
    public CompletableFuture<Cotizacion> updateCotizacion(Long id, Map<String, Object> updates) throws JsonProcessingException {
        Cotizacion cotizacion = cotizacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la cotizacion: " + id));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.updateValue(cotizacion, updates);

        Cotizacion updatedCotizacion = cotizacionRepository.save(cotizacion);
        return CompletableFuture.completedFuture(updatedCotizacion);
    }

    @Async
    public CompletableFuture<ResponseEntity<Map<String, String>>> deleteCotizacion(Long id) {
        Cotizacion cotizacion = cotizacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la cotizacion: " + id));
    
                cotizacionRepository.delete(cotizacion);
    
        Map<String, String> response = new HashMap<>();
        response.put("message", "Cotizacion eliminado correctamente");
        return CompletableFuture.completedFuture(ResponseEntity.ok(response));
    }

     
    @Async
    @Transactional
    public CompletableFuture<List<cotizacionDTO>> obtenerTodasLasCotizacionesConRelaciones() {
        List<cotizacionDTO> cotizacionesDTO = cotizacionRepository.findAll() // Obtener todas las cotizaciones
                .stream()
                .map(cotizacion -> {
                    cotizacionDTO dto = new cotizacionDTO();
                    dto.setId(cotizacion.getId());
                    dto.setCostoArticulo(cotizacion.getCostoArticulo());
                    dto.setCostoFinal(cotizacion.getCostoFinal());
                    dto.setCostoTotalImportacion(cotizacion.getCostoTotalImportacion());
                    dto.setFlete(cotizacion.getFlete());
                    dto.setGastosImportacion(cotizacion.getGastosImportacion());
                    dto.setImpuestos(cotizacion.getImpuestos());
                    dto.setPeso(cotizacion.getPeso());
                    dto.setSeguro(cotizacion.getSeguro());
                    dto.setFechaCotizacion(cotizacion.getFechaCotizacion());
    
                    // Mapear Cliente
                    if (cotizacion.getCliente() != null) {
                        cotizacionDTO.ClienteDTO clienteDTO = new cotizacionDTO.ClienteDTO();
                        clienteDTO.setId(cotizacion.getCliente().getId());
                        clienteDTO.setNombre(cotizacion.getCliente().getNombre());
                        clienteDTO.setCorreo(cotizacion.getCliente().getCorreo());
                        dto.setCliente(clienteDTO);
                    }
    
                    // Mapear TipoProducto
                    if (cotizacion.getTipoProducto() != null) {
                        cotizacionDTO.TipoProductoDTO tipoProductoDTO = new cotizacionDTO.TipoProductoDTO();
                        tipoProductoDTO.setId(cotizacion.getTipoProducto().getId());
                        tipoProductoDTO.setNombre(cotizacion.getTipoProducto().getNombre());
                        tipoProductoDTO.setPorcentajeFlete(cotizacion.getTipoProducto().getPorcentajeFlete());
                        tipoProductoDTO.setPorcentajeGastosImportacion(cotizacion.getTipoProducto().getPorcentajeGastosImportacion());
                        tipoProductoDTO.setPorcentajeSeguro(cotizacion.getTipoProducto().getPorcentajeSeguro());
                        tipoProductoDTO.setPorcentajeImpuestos(cotizacion.getTipoProducto().getPorcentajeImpuestos());
                        dto.setTipoProducto(tipoProductoDTO);
                    }
    
                    return dto;
                })
                .collect(Collectors.toList());
    
        return CompletableFuture.completedFuture(cotizacionesDTO);
    }
}
