package com.uade.tpo.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Locacion;
import com.uade.tpo.marketplace.repository.LocacionRepository;

@Service
public class LocacionServiceImpl implements LocacionService {
    @Autowired
    private LocacionRepository locacionRepository;

    public Locacion createLocacion(String nombre,String direccion, int capacidad_total) {
        return locacionRepository.save(new Locacion(nombre, direccion, capacidad_total));
    }
}
