package com.uade.tpo.marketplace.controllers;

import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> obtenerTodos() {
        return eventoService.obtenerTodos();
    }

    @GetMapping("/disponibles")
    public List<Evento> disponibles() {
        return eventoService.obtenerDisponibles();
    }

    @GetMapping("/buscar")
    public List<Evento> buscar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String artista
    ) {
        if (nombre != null) return eventoService.buscarPorNombre(nombre);
        if (categoria != null) return eventoService.buscarPorCategoria(categoria);
        if (artista != null) return eventoService.buscarPorArtista(artista);
        return eventoService.obtenerTodos();
    }

    @PostMapping
    public Evento crear(@RequestBody Evento evento) {
        return eventoService.crearEvento(evento);
    }

    @PutMapping("/{id}")
    public Evento actualizar(@PathVariable Long id, @RequestBody Evento evento) {
        return eventoService.actualizarEvento(id, evento);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        eventoService.eliminarEvento(id);
    }
}
