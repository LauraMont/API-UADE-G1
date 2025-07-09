package com.uade.tpo.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Artista;
import java.util.Optional;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    @Query("SELECT a FROM Artista a WHERE LOWER(a.nombre) = LOWER(?1)")
    Optional<Artista> findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Artista a WHERE a.id = ?1")
    Artista findByArtista_Id(Long id);
}
