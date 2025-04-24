package com.uade.tpo.marketplace.service;

import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Locacion;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;

@Service
public interface LocacionService {
    public Locacion createLocacion(String nombre, String direccion, String capacidad_total) throws CategoryDuplicateException;
}
