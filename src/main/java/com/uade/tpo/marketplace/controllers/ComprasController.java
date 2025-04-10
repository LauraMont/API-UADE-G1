package com.uade.tpo.marketplace.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Compra;
import com.uade.tpo.marketplace.exceptions.EventNotExistException;
import com.uade.tpo.marketplace.exceptions.StockMaxReached;
import com.uade.tpo.marketplace.exceptions.UserNotExistException;
import com.uade.tpo.marketplace.service.ComprasService;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/compras")
public class ComprasController {
    @Autowired
    private ComprasService comprasService;

    @PostMapping
    public ResponseEntity<Object> createCompra(@RequestBody CompraRequest compra) throws UserNotExistException, EventNotExistException, StockMaxReached {
        int cantidadInt = compra.getCantidad();
        Float totalFloat =compra.getTotal();
        Long idUsuario = compra.getUsuarioId();
        Long idProducto = compra.getEventoId();
        Compra result = comprasService.createCompra(idUsuario, idProducto, cantidadInt, totalFloat);
        CompraRequest response = new CompraRequest(result.getIdUsuario(), result.getIdProducto(), result.getCantidad(), result.getTotal());
        return ResponseEntity.created(URI.create("/compras/" + result.getIdCompra())).body(response);
    }
}
