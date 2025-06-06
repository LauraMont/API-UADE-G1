package com.uade.tpo.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uade.tpo.marketplace.entity.Locacion;

public interface LocacionRepository extends JpaRepository<Locacion, Long> {
    @Query("SELECT e FROM Locacion e WHERE e.id = ?1") 
    Locacion findBy_Id(Long id); // Cambié el nombre del método a findById para que sea más claro y conciso

    @Query("SELECT e FROM Locacion e WHERE e.nombre = ?1 AND e.direccion = ?2")
    Locacion existsByNombreAndDireccion(String nombre, String direccion);
} 
