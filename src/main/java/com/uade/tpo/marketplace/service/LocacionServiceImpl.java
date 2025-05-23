package com.uade.tpo.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Butaca;
import com.uade.tpo.marketplace.entity.Locacion;
import com.uade.tpo.marketplace.entity.Zona;
import com.uade.tpo.marketplace.entity.dto.LocacionRequest;
import com.uade.tpo.marketplace.entity.dto.ZonaRequest;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.marketplace.repository.ButacaRepository;
import com.uade.tpo.marketplace.repository.LocacionRepository;
import com.uade.tpo.marketplace.repository.ZonasRepository;

@Service
public class LocacionServiceImpl implements LocacionService {
    @Autowired
    private LocacionRepository locacionRepository;
    @Autowired
    private ZonasRepository zonasRepository;
    @Autowired
    private ButacaRepository butacaRepository;

    public LocacionRequest createLocacion(String nombre,String direccion, int capacidad_total, List<ZonaRequest> zonas) throws CategoryDuplicateException {
        Locacion locacionExistente = new Locacion(nombre, direccion, capacidad_total);
        locacionRepository.save(locacionExistente);
        char letraZona = 'A';
        for (ZonaRequest zona : zonas) {
            Zona zonaCreada = zonasRepository.save(new Zona(zona.getPrecio_base(), locacionExistente, zona.getCantidad_butacas()));
            for (int i = 0; i < zona.getCantidad_butacas(); i++) {
                String identificador = letraZona + String.valueOf(i);
                butacaRepository.save(new Butaca(identificador, zonaCreada));
            }
            letraZona++;
        }
        return new LocacionRequest(nombre, direccion, capacidad_total);
    }
}
