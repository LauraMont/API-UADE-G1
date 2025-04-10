package com.uade.tpo.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Compra;
import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.exceptions.EventNotExistException;
import com.uade.tpo.marketplace.exceptions.StockMaxReached;
import com.uade.tpo.marketplace.exceptions.UserNotExistException;
import com.uade.tpo.marketplace.repository.ComprasRepository;
import com.uade.tpo.marketplace.repository.EventoRepository;
import com.uade.tpo.marketplace.repository.UserRepository;

@Service
public class ComprasServiceImpl implements ComprasService{
    @Autowired
    private EventoRepository eventosRepository ;
    @Autowired
    private ComprasRepository comprasRepository;
    @Autowired
    private UserRepository usuariosRepository;

    public Compra createCompra(Long idUsuario, Long idEvento, int cantidad, float total) throws UserNotExistException, EventNotExistException, StockMaxReached {
        Usuario user = (Usuario) this.usuariosRepository.findById(idUsuario).orElseThrow(() -> new UserNotExistException());
        Evento evento = (Evento) this.eventosRepository.findById(idEvento).orElseThrow(() -> new EventNotExistException());
        if(evento.getStockEntradas() - cantidad < 0) {
            throw new StockMaxReached();
        }
        this.eventosRepository.updateStock(idEvento, evento.getStockEntradas() - cantidad);
        if(cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
        if(total <= 0) {
            throw new IllegalArgumentException("El total debe ser mayor a cero.");
        }
        return (Compra) this.comprasRepository.save(new Compra(user, evento, cantidad, total));
    }
}
