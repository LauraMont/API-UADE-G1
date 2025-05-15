package com.uade.tpo.marketplace.controllers;

import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.entity.dto.EventoRequest;
import com.uade.tpo.marketplace.exceptions.ArtistaNotExistException;
import com.uade.tpo.marketplace.exceptions.EventDuplicateException;
import com.uade.tpo.marketplace.exceptions.EventNotExistException;
import com.uade.tpo.marketplace.exceptions.LocacionNotExistException;
import com.uade.tpo.marketplace.service.EventoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/disponibles") // all users can see available events
    public List<Evento> disponibles() {
        return eventoService.obtenerDisponibles();
    }

    @GetMapping("/buscar") // all users can search events by name, category or artist
    public ResponseEntity<List<Evento>> buscar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String artista
    ) {
        if (nombre != null) return ResponseEntity.ok(eventoService.buscarPorNombre(nombre));
        if (categoria != null) return ResponseEntity.ok(eventoService.buscarPorCategoria(categoria));
        if (artista != null) return ResponseEntity.ok(eventoService.buscarPorArtista(artista));
        return ResponseEntity.ok(eventoService.getEventos(PageRequest.of(0, Integer.MAX_VALUE)).getContent()); // Return all events if no filter is applied
    }

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
    public ResponseEntity<Evento> crearEvento( @RequestBody EventoRequest request) throws EventDuplicateException, ArtistaNotExistException, LocacionNotExistException {
        Evento nuevoEvento = eventoService.crearEvento(request.getNombre(), request.getDescripcion(), request.getFechaHora(), request.getLocacion(), request.getArtista(), request.getEstado(), request.getCategoria());
        return ResponseEntity.ok(nuevoEvento);
    }

    @GetMapping("/{id}") // all users can see all events
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) throws EventNotExistException {
        return ResponseEntity.ok(eventoService.getEventoById(id));
    }
    @PutMapping("/{id}") // only admin can edit events
    public ResponseEntity<String> editEvento(@PathVariable Long id, @RequestBody EventoRequest request) throws EventNotExistException {
        eventoService.editEvento(id, request.getNombre(), request.getDescripcion(), request.getFechaHora(), request.getLocacion(), request.getArtista(), request.getEstado(), request.getCategoria(), request.getStockEntradas());
        return ResponseEntity.ok("Evento editado correctamente");
    }
    @DeleteMapping("/{id}") // only admin can delete events
    public ResponseEntity<String> deleteEvento(@PathVariable Long id) throws EventNotExistException {
        eventoService.deleteEvento(id);
        return ResponseEntity.ok("Evento eliminado correctamente");
    }
}
