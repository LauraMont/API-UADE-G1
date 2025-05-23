package com.uade.tpo.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Zona;

@Repository
public interface ZonasRepository extends JpaRepository<Zona, Long> {

    default Zona[] findAllByLocation(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByLocation'");
    }
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar zonas por nombre o ubicación
    // List<Zona> findByNombre(String nombre);
    
}
