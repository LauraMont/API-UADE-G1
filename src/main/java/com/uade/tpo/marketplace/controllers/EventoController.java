package com.uade.tpo.marketplace.controllers;

import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<Evento> crearEvento( @RequestBody EventoRequest request) {
        Evento nuevoEvento = eventoService.crearEvento(request);
        return ResponseEntity.ok(nuevoEvento);
    }
}
