package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.entity.Compra;
import com.uade.tpo.marketplace.exceptions.EventNotExistException;
import com.uade.tpo.marketplace.exceptions.StockMaxReached;
import com.uade.tpo.marketplace.exceptions.UserNotExistException;

public interface ComprasService {
    public Compra createCompra(Long idUsuario, Long idProducto, int cantidad, float total) throws UserNotExistException, EventNotExistException, StockMaxReached ;
}
