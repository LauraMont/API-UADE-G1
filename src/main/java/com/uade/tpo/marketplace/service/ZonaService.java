package com.uade.tpo.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.entity.Zona;
import com.uade.tpo.marketplace.exceptions.EventNotExistException;
import com.uade.tpo.marketplace.repository.EventoRepository;
import com.uade.tpo.marketplace.repository.LocacionRepository;
import com.uade.tpo.marketplace.repository.ZonasRepository;

@Service
public class ZonaService {

    @Autowired
    private ZonasRepository zonaRepository;

    @Autowired EventoRepository eventoRepository;

    public List<Zona> obtenerZonasPorLocacionId(Long locacacionId) throws EventNotExistException {
        return zonaRepository.findByLocacionId(locacacionId);
    }
}
