package com.uade.tpo.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Compra;
import com.uade.tpo.marketplace.exceptions.EventNotExistException;
import com.uade.tpo.marketplace.exceptions.UserNotExistException;
import com.uade.tpo.marketplace.repository.ComprasRepository;

@Service
public class ComprasServiceImpl implements ComprasService{
    @Autowired
    private ComprasRepository comprasRepository;

    public Compra createCompra(Long idUsuario, Long idEvento, int cantidad, float total) throws UserNotExistException, EventNotExistException {
        if(!this.comprasRepository.existsByIdUser(idUsuario)) {
            throw new UserNotExistException();
        }
        if(this.comprasRepository.existsByIdEvent(idEvento)) {
            throw new EventNotExistException();
        }
        if(cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
        if(total <= 0) {
            throw new IllegalArgumentException("El total debe ser mayor a cero.");
        }
        return (Compra) this.comprasRepository.save(new Compra(idUsuario, idEvento, cantidad, total));
    }
}
