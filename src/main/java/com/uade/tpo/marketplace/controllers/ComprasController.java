package com.uade.tpo.marketplace.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Compra;
import com.uade.tpo.marketplace.entity.dto.CompraRequest;
import com.uade.tpo.marketplace.exceptions.ButacaNoExisteException;
import com.uade.tpo.marketplace.exceptions.ButacaVendidaException;
import com.uade.tpo.marketplace.exceptions.EventNotExistException;
import com.uade.tpo.marketplace.exceptions.StockMaxReached;
import com.uade.tpo.marketplace.exceptions.UserNotExistException;
import com.uade.tpo.marketplace.service.ComprasService;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/compras")
public class ComprasController {
    @Autowired
    private ComprasService comprasService;

    @PostMapping
    public ResponseEntity<Object> createCompra(@RequestBody CompraRequest compra) throws UserNotExistException, EventNotExistException, StockMaxReached, ButacaNoExisteException, ButacaVendidaException {
        List<Long> butacas = compra.getButacas();
        Long idUsuario = compra.getUsuarioId();
        Long idProducto = compra.getEventoId();
        Compra result = comprasService.createCompra(idUsuario, idProducto, butacas);
        CompraRequest response = new CompraRequest(result.getIdUsuario(), result.getIdProducto(), butacas);
        return ResponseEntity.created(URI.create("/compras/" + result.getIdCompra())).body(response);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Compra>> getComprasByUsuario(@PathVariable Long usuarioId) throws UserNotExistException {
        List<Compra> compras = comprasService.getComprasByUsuarioId(usuarioId);
        return ResponseEntity.ok(compras);
    }
}
