package com.uade.tpo.marketplace.repository;

import com.uade.tpo.marketplace.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query(value = "SELECT c FROM Categoria c WHERE LOWER(c.nombre) = LOWER(?1)") 
    Optional<Categoria> findByNombreIgnoreCase(String nombre);
}
