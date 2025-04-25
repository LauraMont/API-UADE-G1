package com.uade.tpo.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    @Query("SELECT e FROM Artista e WHERE e.id = ?1")
    Artista findByArtista_Id(String artistaId);
}
