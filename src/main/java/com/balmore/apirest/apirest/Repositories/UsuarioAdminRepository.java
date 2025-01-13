package com.balmore.apirest.apirest.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balmore.apirest.apirest.Entities.UsuarioAdmin;

public interface UsuarioAdminRepository extends JpaRepository<UsuarioAdmin, Long>{
    boolean existsByNombreUsuario(String nombreUsuario);
    Optional<UsuarioAdmin> findByNombreUsuario(String nombreUsuario);
}