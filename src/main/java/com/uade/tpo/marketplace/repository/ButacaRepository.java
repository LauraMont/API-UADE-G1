package com.uade.tpo.marketplace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Butaca;
import com.uade.tpo.marketplace.entity.Usuario;

@Repository
public interface ButacaRepository extends JpaRepository<Butaca, Long> {

    @Query("SELECT b FROM Butaca b WHERE b.numero = ?1")
    Butaca findByName(String butaca);

    @Query("SELECT b FROM Butaca b WHERE b.zona = ?1")
    List<Butaca> findByZonaId(Long zonaId);
    
    
}
