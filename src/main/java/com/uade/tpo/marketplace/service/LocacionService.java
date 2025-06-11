package com.uade.tpo.marketplace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Locacion;
import com.uade.tpo.marketplace.entity.dto.LocacionRequest;
import com.uade.tpo.marketplace.entity.dto.ZonaRequest;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.marketplace.exceptions.LocacionDuplicadaException;

@Service
public interface LocacionService {
    public LocacionRequest createLocacion(String nombre, String direccion, List<ZonaRequest> zonas) throws CategoryDuplicateException, LocacionDuplicadaException;

    public LocacionRequest getLocacionById(Long locacionId);
}
