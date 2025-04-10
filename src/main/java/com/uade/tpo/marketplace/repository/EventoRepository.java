package com.uade.tpo.marketplace.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.enums.EstadoEvento;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
   @Modifying
   @Transactional
   @Query("UPDATE Evento e SET e.cant_entradas = ?2 WHERE e.id = ?1")
   void updateStock(Long eventoId, int stock_entradas); 

    @Modifying
    @Transactional
    @Query("UPDATE Evento e SET e.nombre = ?2, e.descripcion = ?3, e.fecha_hora = ?4, e.artista = ?5, e.estado = ?6, e.categoria = ?7, e.cant_entradas = ?8 WHERE e.id = ?1")
    void updateEvento(Long eventoId, String nombre, String descripcion, Date fechaHora, String artista, EstadoEvento estado, Categoria categoria, int cantEntradas);


    @Query("SELECT e FROM Evento e WHERE e.nombre = ?1")
    List<Evento> findByNombre(String nombre); 
    List<Evento> findByNombreContainingIgnoreCase(String nombre);
    List<Evento> findByCategoriaNombreContainingIgnoreCase(String categoria);
    List<Evento> findByArtistaContainingIgnoreCase(String artista);
    List<Evento> findByStockEntradasGreaterThan(int cantidad);
}
