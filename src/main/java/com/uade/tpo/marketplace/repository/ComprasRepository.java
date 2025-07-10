package com.uade.tpo.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Compra;
import com.uade.tpo.marketplace.entity.Usuario;

@Repository
public interface ComprasRepository extends JpaRepository<Compra,Long> {
    List<Compra> findByUsuario(Usuario usuario);
    
    @Query("SELECT c FROM Compra c WHERE c.usuario.id = :usuarioId")
    List<Compra> findByUsuarioId(@Param("usuarioId") Long usuarioId);
}