package com.uade.tpo.marketplace.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Butaca;
import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.entity.dto.LocacionRequest;
import com.uade.tpo.marketplace.entity.dto.ZonaRequest;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.marketplace.exceptions.EventNotExistException;
import com.uade.tpo.marketplace.exceptions.LocacionDuplicadaException;
import com.uade.tpo.marketplace.exceptions.LocacionNotExistException;
import com.uade.tpo.marketplace.service.ButacaService;
import com.uade.tpo.marketplace.service.LocacionService;
import com.uade.tpo.marketplace.service.ZonaService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/locacion")
public class LocactionController {
     @Autowired
    private LocacionService locacionService;

    @Autowired
    private ZonaService zonaService;

    @Autowired
    private ButacaService butacaService;
    
    @PostMapping
    public ResponseEntity<LocacionRequest> createLocation(@RequestBody LocacionRequest locacionRequest) throws CategoryDuplicateException, LocacionDuplicadaException {
        LocacionRequest result = locacionService.createLocacion(locacionRequest.getNombre(), locacionRequest.getDireccion(), locacionRequest.getZonas());
        return ResponseEntity.created(URI.create("/locacion/")).body(result);
    }

    @GetMapping("/id/{locacionId}")
    public ResponseEntity<LocacionRequest> getLocacionById(@PathVariable Long locacionId) throws LocacionNotExistException {
        LocacionRequest locacion = locacionService.getLocacionById(locacionId);
        return ResponseEntity.ok(locacion);
    }

    // ✅ Obtener zonas asociadas a una locación
    @GetMapping("/{locacionId}/zonas")
    public ResponseEntity<List<ZonaRequest>> obtenerZonasPorLocacion(@PathVariable Long locacionId) throws LocacionNotExistException, EventNotExistException {
        List<ZonaRequest> zonas = zonaService.obtenerZonasPorLocacionId(locacionId)
                .stream()
                .map(zona -> new ZonaRequest(zona.getPrecio_base(), zona.getCantidad_butacas()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(zonas);
    }

    // ✅ Obtener butacas de una zona específica
    @GetMapping("/zona/{zonaId}/butacas")
    public ResponseEntity<List<Butaca>> obtenerButacasPorZona(@PathVariable Long zonaId) {
        List<Butaca> butacas = butacaService.obtenerButacasPorZona(zonaId);
        return ResponseEntity.ok(butacas);
    }
}
