package com.uade.tpo.marketplace.controllers;

import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.exceptions.EventDuplicateException;
import com.uade.tpo.marketplace.exceptions.EventNotExistException;
import com.uade.tpo.marketplace.service.EventoService;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping // all users can see all events
    public ResponseEntity<Page<Evento>> getEventos(
        @RequestParam(required = false) Integer page, 
        @RequestParam(required = false) Integer size) {
            if (page == null || size == null) {
                return ResponseEntity.ok(eventoService.getEventos(PageRequest.of(0, Integer.MAX_VALUE)));
            }
        return ResponseEntity.ok(eventoService.getEventos(PageRequest.of(page, size)));
    }

    @PostMapping // only admin can create events
    public ResponseEntity<Evento> crearEvento( @RequestBody EventoRequest request) throws EventDuplicateException {
        Evento nuevoEvento = eventoService.crearEvento(request.getNombre(), request.getDescripcion(), Date.valueOf(request.getFecha_hora()), request.getEstado(), request.getCategoria(), request.getCant_entradas());
        return ResponseEntity.ok(nuevoEvento);
    }

    @GetMapping("/{id}") // all users can see all events
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) throws EventNotExistException {
        return ResponseEntity.ok(eventoService.getEventoById(id));
    }
    @PutMapping("/{id}") // only admin can edit events
    public ResponseEntity<Void> editEvento(@PathVariable Long id, @RequestBody EventoRequest request) throws EventNotExistException {
        eventoService.editEvento(id, request.getNombre(), request.getDescripcion(), Date.valueOf(request.getFecha_hora()), request.getEstado(), request.getCategoria(), request.getCant_entradas());
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}") // only admin can delete events
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) throws EventNotExistException {
        eventoService.deleteEvento(id);
        return ResponseEntity.ok().build();
    }
}
