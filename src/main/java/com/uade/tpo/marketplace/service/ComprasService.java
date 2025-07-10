package com.uade.tpo.marketplace.service;

import java.util.List;

import com.uade.tpo.marketplace.entity.Compra;
import com.uade.tpo.marketplace.exceptions.ButacaNoExisteException;
import com.uade.tpo.marketplace.exceptions.ButacaVendidaException;
import com.uade.tpo.marketplace.exceptions.EventNotExistException;
import com.uade.tpo.marketplace.exceptions.StockMaxReached;
import com.uade.tpo.marketplace.exceptions.UserNotExistException;

public interface ComprasService {
    public Compra createCompra(Long idUsuario, Long idProducto, List<Long> butacas) throws UserNotExistException, EventNotExistException, StockMaxReached, ButacaNoExisteException, ButacaVendidaException;
    public List<Compra> getComprasByUsuarioId(Long usuarioId) throws UserNotExistException;
}
