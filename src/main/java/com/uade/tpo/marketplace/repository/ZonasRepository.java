package com.uade.tpo.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Zona;

@Repository
public interface ZonasRepository extends JpaRepository<Zona, Long> {

    @Query("SELECT z FROM Zona z WHERE z.locacion = ?1")
    List<Zona> findByLocacionId(Long locacionId);
    
}
