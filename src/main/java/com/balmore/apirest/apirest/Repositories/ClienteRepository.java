package com.balmore.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balmore.apirest.apirest.Entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
