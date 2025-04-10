package com.uade.tpo.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.marketplace.entity.Evento;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
//    @Modifying
//    @Transactional
//    @Query("UPDATE Evento e SET e.cant_entradas = ?2 WHERE e.id = ?1")
//    void updateStock(Long eventoId, int stock_entradas); // Cambia el nombre del método para que coincida con el de la interfaz JpaRepository
    List<Evento> findByNombreContainingIgnoreCase(String nombre);
    List<Evento> findByCategoriaNombreContainingIgnoreCase(String categoria);
    List<Evento> findByArtistaContainingIgnoreCase(String artista);
    List<Evento> findByStockEntradasGreaterThan(int cantidad);
}
