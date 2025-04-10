package com.uade.tpo.marketplace.repository;

import com.uade.tpo.marketplace.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    //     @Query(value = "select c from Categories where c.description =?1") //Define una Query en SQL // * == c
    Optional<Categoria> findByNombreIgnoreCase(String nombre);
}
