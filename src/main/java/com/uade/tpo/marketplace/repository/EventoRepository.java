package com.uade.tpo.marketplace.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.marketplace.entity.Artista;
import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.entity.Locacion;
import com.uade.tpo.marketplace.enums.EstadoEvento;


@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
   @Modifying
   @Transactional
   @Query("UPDATE Evento e SET e.cantEntradas = ?2 WHERE e.id = ?1")
   void updateStock(Long eventoId, int stockEntradas); 

    @Modifying
    @Transactional
    @Query("UPDATE Evento e SET e.nombre = ?2, e.descripcion = ?3, e.fechaHora = ?4, e.artista = ?5,e.locacion=?6  ,e.estado = ?7, e.categoria = ?8, e.cantEntradas = ?9 WHERE e.id = ?1")
    void updateEvento(Long eventoId, String nombre, String descripcion, Date fechaHora, Artista artista, Locacion locacion, EstadoEvento estado, Categoria categoria, int cantEntradas);


    @Query("SELECT e FROM Evento e WHERE e.nombre = ?1")
    List<Evento> findByNombre(String nombre); 

    @Query("SELECT e FROM Evento e WHERE LOWER(e.nombre) = LOWER(?1)")
    List<Evento> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT e FROM Evento e WHERE e.categoria = ?1")
    List<Evento> findByCategoriaId(Long categoriaId);

    @Query("SELECT e FROM Evento e WHERE LOWER(e.artista) = LOWER(?1)")
    List<Evento> findByArtistaContainingIgnoreCase(String artista);

    @Query("SELECT e FROM Evento e WHERE e.stockEntradas > ?1")
    List<Evento> findByStockEntradasGreaterThan(int cantidad);

    @Query("SELECT e FROM Evento e WHERE e.artista = ?1")
    List<Evento> findByArtista_Id(String artista);
}
