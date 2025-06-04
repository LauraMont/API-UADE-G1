package com.uade.tpo.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.RenglonDeCompra;

@Repository
public interface RenglonCompraRepository extends JpaRepository<RenglonDeCompra, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar renglones de compra por producto o usuario
    // List<RenglonCompra> findByProducto(Producto producto);
    // List<RenglonCompra> findByUsuario(Usuario usuario);

}
