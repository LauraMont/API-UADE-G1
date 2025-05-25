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
import java.sql.Date;

import com.uade.tpo.marketplace.enums.EstadoEvento;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/disponibles") 
    public List<Evento> disponibles() {
        return eventoService.obtenerDisponibles();
    }

    @GetMapping("/buscar") 
    public ResponseEntity<List<Evento>> buscar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String artista,
            @RequestParam(required = false) EstadoEvento estado,
            @RequestParam(required = false) String locacion,
            @RequestParam(required = false) Integer stockMin
    ) {
        if (estado != null || locacion != null || stockMin != null) {
            return ResponseEntity.ok(eventoService.buscarPorFiltros(nombre, categoria, artista, estado, null, null, stockMin));
        }
        
        if (nombre != null) return ResponseEntity.ok(eventoService.buscarPorNombre(nombre));
        if (categoria != null) return ResponseEntity.ok(eventoService.buscarPorCategoria(categoria));
        if (artista != null) return ResponseEntity.ok(eventoService.buscarPorArtista(artista));
        return ResponseEntity.ok(eventoService.getEventos(PageRequest.of(0, Integer.MAX_VALUE)).getContent());
    }

    @GetMapping("/estado/{estado}") 
    public ResponseEntity<List<Evento>> buscarPorEstado(@PathVariable EstadoEvento estado) {
        return ResponseEntity.ok(eventoService.buscarPorEstado(estado));
    }

    @GetMapping("/locacion/{locacionId}") 
    public ResponseEntity<List<Evento>> buscarPorLocacion(@PathVariable String locacionId) {
        return ResponseEntity.ok(eventoService.buscarPorLocacion(locacionId));
    }

    @GetMapping
    public ResponseEntity<Page<Evento>> getEventos(
        @RequestParam(required = false) Integer page, 
        @RequestParam(required = false) Integer size) {
            if (page == null || size == null) {
                return ResponseEntity.ok(eventoService.getEventos(PageRequest.of(0, Integer.MAX_VALUE)));
            }
        return ResponseEntity.ok(eventoService.getEventos(PageRequest.of(page, size)));
    }

    @PostMapping 
    public ResponseEntity<Evento> crearEvento(@RequestBody EventoRequest request) throws EventDuplicateException, ArtistaNotExistException, LocacionNotExistException {
        Evento nuevoEvento = eventoService.crearEvento(
            request.getNombre(), 
            request.getDescripcion(), 
            request.getFechaHora(), 
            request.getArtistaId(), 
            request.getLocacionId(), 
            request.getEstado(), 
            request.getCategoriaId()
        );
        return ResponseEntity.ok(nuevoEvento);
    }

    @GetMapping("/{id}") 
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) throws EventNotExistException {
        return ResponseEntity.ok(eventoService.getEventoById(id));
    }

    @PutMapping("/{id}") 
    public ResponseEntity<String> editEvento(@PathVariable Long id, @RequestBody EventoRequest request) throws EventNotExistException {
        eventoService.editEvento(
            id, 
            request.getNombre(), 
            request.getDescripcion(), 
            request.getFechaHora(), 
            request.getArtistaId(), 
            request.getLocacionId(), 
            request.getEstado(), 
            request.getCategoriaId(), 
            request.getStockEntradas()
        );
        return ResponseEntity.ok("Evento editado correctamente");
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity<String> deleteEvento(@PathVariable Long id) throws EventNotExistException {
        eventoService.deleteEvento(id);
        return ResponseEntity.ok("Evento eliminado correctamente");
    }
}
