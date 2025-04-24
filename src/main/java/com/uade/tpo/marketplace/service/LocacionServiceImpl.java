package com.uade.tpo.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.uade.tpo.marketplace.entity.Locacion;
import com.uade.tpo.marketplace.repository.LocacionRepository;

public class LocacionServiceImpl implements LocacionService {
    @Autowired
    private LocacionRepository locacionRepository;

    public Locacion createLocacion(String nombre, String descripcion, String direccion) {
        return locacionRepository.save(new Locacion(nombre, descripcion, direccion));
    }
}
