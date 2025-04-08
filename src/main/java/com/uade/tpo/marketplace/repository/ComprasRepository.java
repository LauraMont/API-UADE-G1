package com.uade.tpo.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Compra;

@Repository
public interface ComprasRepository extends JpaRepository<Compra,Long> {
    
    @Query(value = "select u from Usuario u where u.id =?1")
    boolean existsByIdUser(Long idUsuario);

    @Query(value = "select u from Evento u where u.id =?1")
    boolean existsByIdEvent(Long idEvento);
}