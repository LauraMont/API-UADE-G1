package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.controllers.EventoRequest;
import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Evento crearEvento(EventoRequest request) {
        Evento evento = new Evento();
        evento.setNombre(request.getNombre());
        evento.setCategoria(request.getCategoria());
        evento.setCant_entradas(request.getCant_entradas());
        return eventoRepository.save(evento);
    }
}

