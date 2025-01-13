package com.balmore.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balmore.apirest.apirest.Entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
}
