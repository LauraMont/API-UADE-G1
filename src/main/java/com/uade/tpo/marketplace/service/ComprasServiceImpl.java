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
import com.uade.tpo.marketplace.entity.dto.ZonaRequest;
import com.uade.tpo.marketplace.enums.EstadoButaca;
import com.uade.tpo.marketplace.exceptions.ButacaNoExisteException;
import com.uade.tpo.marketplace.exceptions.ButacaVendidaException;
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

    public Compra createCompra(Long idUsuario, Long idEvento, List<Long> butacas) throws UserNotExistException, EventNotExistException, StockMaxReached, ButacaNoExisteException, ButacaVendidaException {
        Usuario user = (Usuario) this.usuariosRepository.findById(idUsuario).orElseThrow(() -> new UserNotExistException());
        Evento evento = (Evento) this.eventosRepository.findById(idEvento).orElseThrow(() -> new EventNotExistException());
        if(evento.getStockEntradas() - butacas.size() <= 0) {
            throw new StockMaxReached();
        }
       Compra compra = this.comprasRepository.save(new Compra(user, evento,0));
       float totalCompra = 0;
        for(Long butacaId : butacas) {
            Butaca butacaExistente = (Butaca) this.butacaRepository.findBy_Id(butacaId);
            if(butacaExistente == null) {
                throw new ButacaNoExisteException();
            }
            if(butacaExistente.getEstado() == EstadoButaca.VENDIDA) {
                throw new ButacaVendidaException();
            }
            butacaExistente.setEstado(EstadoButaca.VENDIDA);
            butacaRepository.save(butacaExistente);
            RenglonDeCompra renglon = renglonCompraRepositor.save(new RenglonDeCompra(compra, butacaExistente));
            Entrada entrada = new Entrada(butacaExistente);
            entrada.setRenglondecompra(renglon);  // ← setear la relación correctamente
            this.entradasRepository.save(entrada);

            totalCompra += butacaExistente.getZona().getPrecio_base();
        }

        compra.setTotal(totalCompra);
        this.eventosRepository.updateStock(idEvento, evento.getStockEntradas() - butacas.size());
        return compra;
    }
}
