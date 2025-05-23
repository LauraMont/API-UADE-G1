package com.uade.tpo.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Butaca;
import com.uade.tpo.marketplace.entity.Compra;
import com.uade.tpo.marketplace.entity.Entrada;
import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.entity.RenglonDeCompra;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.enums.EstadoButaca;
import com.uade.tpo.marketplace.exceptions.EventNotExistException;
import com.uade.tpo.marketplace.exceptions.StockMaxReached;
import com.uade.tpo.marketplace.exceptions.UserNotExistException;
import com.uade.tpo.marketplace.repository.ButacaRepository;
import com.uade.tpo.marketplace.repository.ComprasRepository;
import com.uade.tpo.marketplace.repository.EntradaRepository;
import com.uade.tpo.marketplace.repository.EventoRepository;
import com.uade.tpo.marketplace.repository.RenglonCompraRepository;
import com.uade.tpo.marketplace.repository.UserRepository;

@Service
public class ComprasServiceImpl implements ComprasService{
    @Autowired
    private EventoRepository eventosRepository ;
    @Autowired
    private ComprasRepository comprasRepository;
    @Autowired
    private UserRepository usuariosRepository;
    @Autowired
    private ButacaRepository butacaRepository;
    @Autowired
    private EntradaRepository entradasRepository;
    @Autowired
    private RenglonCompraRepository renglonCompraRepositor;

    public Compra createCompra(Long idUsuario, Long idEvento, List<String> butacas) throws UserNotExistException, EventNotExistException, StockMaxReached {
        Usuario user = (Usuario) this.usuariosRepository.findById(idUsuario).orElseThrow(() -> new UserNotExistException());
        Evento evento = (Evento) this.eventosRepository.findById(idEvento).orElseThrow(() -> new EventNotExistException());
        if(evento.getStockEntradas() - butacas.size() <= 0) {
            throw new StockMaxReached();
        }
        this.eventosRepository.updateStock(idEvento, evento.getStockEntradas() - butacas.size());
       Compra compra = this.comprasRepository.save(new Compra(user, evento,0));
       float totalCompra = 0;
        for(String butaca : butacas) {
            Butaca butacaExistente = this.butacaRepository.findByName(butaca);
            renglonCompraRepositor.save(new RenglonDeCompra(compra, butacaExistente));
            if(butacaExistente.getEstado() == EstadoButaca.VENDIDA) {
                throw new StockMaxReached();
            }
            this.entradasRepository.save(new Entrada(butacaExistente));
            totalCompra += butacaExistente.getZona().getPrecio_base();
        }
        compra.setTotal(totalCompra);
        return compra;
    }
}
