package com.uade.tpo.marketplace.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Butaca;
import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.entity.Zona;
import com.uade.tpo.marketplace.entity.dto.LocacionRequest;
import com.uade.tpo.marketplace.entity.dto.ZonaRequest;
import com.uade.tpo.marketplace.entity.dto.ZonaResponse;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.marketplace.exceptions.EventNotExistException;
import com.uade.tpo.marketplace.exceptions.LocacionDuplicadaException;
import com.uade.tpo.marketplace.exceptions.LocacionNotExistException;
import com.uade.tpo.marketplace.service.ButacaService;
import com.uade.tpo.marketplace.service.EventoService;
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

    @Autowired
    private EventoService eventoService;
    
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

    @GetMapping("/{locacionId}/zonas")
    public ResponseEntity<List<ZonaResponse>> obtenerZonasPorLocacion(
            @PathVariable Long locacionId,
            @RequestParam(required = false) Long eventoId
    ) throws LocacionNotExistException, EventNotExistException {
        List<ZonaResponse> zonas = zonaService.obtenerZonasConPrecioFinal(locacionId, eventoId);
        return ResponseEntity.ok(zonas);
    }

    // ✅ Obtener butacas de una zona específica
    @GetMapping("/zona/{zonaId}/butacas")
    public ResponseEntity<List<Butaca>> obtenerButacasPorZona(@PathVariable Long zonaId) {
        List<Butaca> butacas = butacaService.obtenerButacasPorZona(zonaId, false);
        return ResponseEntity.ok(butacas);
    }

    // ✅ Obtener butacas disponibles de una zona específica
    @GetMapping("/zona/{zonaId}/butacas/disponibles")
    public ResponseEntity<List<Butaca>> obtenerButacasPorZonaDisponibles(@PathVariable Long zonaId) {
        List<Butaca> butacas = butacaService.obtenerButacasPorZona(zonaId, true);
        return ResponseEntity.ok(butacas);
    }
}
