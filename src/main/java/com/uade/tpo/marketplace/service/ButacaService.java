package com.uade.tpo.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Butaca;
import com.uade.tpo.marketplace.repository.ButacaRepository;

@Service
public class ButacaService {

    @Autowired
    private ButacaRepository butacaRepository;

    public List<Butaca> obtenerButacasPorZona(Long zonaId) {
        return butacaRepository.findByZonaId(zonaId);
    }
}
