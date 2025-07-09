package com.uade.tpo.marketplace.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.uade.tpo.marketplace.entity.Artista;
import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.entity.Locacion;
import com.uade.tpo.marketplace.enums.EstadoEvento;


@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
   @Modifying
   @Transactional
   @Query("UPDATE Evento e SET e.stockEntradas = ?2 WHERE e.id = ?1")
   void updateStock(Long eventoId, int stockEntradas); 

    @Modifying
    @Transactional
    @Query("UPDATE Evento e SET e.nombre = ?2, e.descripcion = ?3, e.fechaHora = ?4, e.artista = ?5,e.locacion=?6  ,e.estado = ?7, e.categoria = ?8, e.stockEntradas = ?9, e.imagenEvento = ?10, e.imagenZonas = ?11 WHERE e.id = ?1")
    void updateEvento(Long eventoId, String nombre, String descripcion, Date fechaHora, Artista artista, Locacion locacion, EstadoEvento estado, Categoria categoria, int cantEntradas, byte[] imagenEvento, byte[] imagenZonas);


    @Query("SELECT e FROM Evento e WHERE e.nombre = ?1")
    List<Evento> findByNombre(String nombre); 

    @Query("SELECT e FROM Evento e WHERE LOWER(e.nombre) = LOWER(?1)")
    List<Evento> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT e FROM Evento e WHERE LOWER(e.nombre) LIKE LOWER(CONCAT('%', ?1, '%'))")
    Page<Evento> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    @Query("SELECT e FROM Evento e WHERE e.categoria = ?1")
    List<Evento> findByCategoriaId(Long categoriaId);

    @Query("SELECT e FROM Evento e WHERE e.categoria.id = ?1")
    Page<Evento> findByCategoriaId(Long categoriaId, Pageable pageable);

    @Query("SELECT e FROM Evento e WHERE LOWER(e.artista.nombre) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Evento> findByArtistaContainingIgnoreCase(String artista);

    @Query("SELECT e FROM Evento e WHERE e.stockEntradas > ?1")
    List<Evento> findByStockEntradasGreaterThan(int cantidad);

    @Query("SELECT e FROM Evento e WHERE e.artista.id = ?1")
    List<Evento> findByArtista_Id(Long artistaId);

    @Query("SELECT e FROM Evento e WHERE e.id = ?1")
    Evento findBy_Id(Long id);

    @Query("SELECT e FROM Evento e WHERE e.locacion.id = ?1 AND e.fechaHora = ?2")
    List<Evento> findByLocacionIdAndFecha(Long locacionIdLong, Date fecha_hora);

    @Query("SELECT e FROM Evento e WHERE e.locacion.id = ?1")
    List<Evento> findByLocacionId(Long locacionId);

    @Query("SELECT e FROM Evento e WHERE e.locacion.id = ?1")
    Page<Evento> findByLocacionId(Long locacionId, Pageable pageable);
}
