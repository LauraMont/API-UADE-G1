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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
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
    public ResponseEntity<?> buscar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String artista,
            @RequestParam(required = false) Long locacionId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        if (nombre != null) {
            if (page != null && size != null) {
                return ResponseEntity.ok(eventoService.buscarPorNombre(nombre, PageRequest.of(page, size)));
            }
            return ResponseEntity.ok(eventoService.buscarPorNombre(nombre));
        }
        if (categoria != null) {
            try {
                Long categoriaId = Long.parseLong(categoria);
                if (page != null && size != null) {
                    return ResponseEntity.ok(eventoService.getEventosPorCategoria(categoriaId, PageRequest.of(page, size)));
                }
                return ResponseEntity.ok(eventoService.getEventosPorCategoria(categoriaId, PageRequest.of(0, 6)));
            } catch (NumberFormatException e) {
                return ResponseEntity.ok(eventoService.buscarPorCategoria(categoria));
            }
        }
        if (artista != null) return ResponseEntity.ok(eventoService.buscarPorArtista(artista));
        if (locacionId != null) {
            if (page != null && size != null) {
                return ResponseEntity.ok(eventoService.getEventosPorLocacion(locacionId, PageRequest.of(page, size)));
            }
            return ResponseEntity.ok(eventoService.buscarPorLocacion(locacionId));
        }
        return ResponseEntity.ok(eventoService.getEventos(PageRequest.of(0, Integer.MAX_VALUE)).getContent()); // Return all events if no filter is applied
    }

    @GetMapping // all users can see all events
    public ResponseEntity<Page<Evento>> getEventos(
        @RequestParam(required = false) Integer page, 
        @RequestParam(required = false) Integer size) {
            if (page == null || size == null) {
                return ResponseEntity.ok(eventoService.getEventos(PageRequest.of(0, 6)));
            }
        return ResponseEntity.ok(eventoService.getEventos(PageRequest.of(page, size)));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //@RequestMapping(value = "/eventos", method=RequestMethod.POST)  
    public ResponseEntity<Evento> crearEvento(@ModelAttribute EventoRequest request) throws EventDuplicateException, ArtistaNotExistException, LocacionNotExistException, IOException {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(request.getFechaHora());
        LocalDateTime localDateTime = offsetDateTime.toLocalDateTime();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());        
        byte[] imagenEventoBase64 = request.getImagenEvento().getBytes();
        byte[] imagenZonasBase64 = request.getImagenZonas().getBytes();
        Evento nuevoEvento = eventoService.crearEvento(
            request.getNombre(), 
            request.getDescripcion(), 
            date, 
            request.getArtista(), 
            request.getLocacion(),
            request.getEstado(), 
            request.getCategoria(), 
            request.getPdescuento(), 
            imagenEventoBase64, 
            imagenZonasBase64
        );
        return ResponseEntity.ok(nuevoEvento);
    }

    @GetMapping("/{id}") // all users can see all events
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) throws EventNotExistException {
        return ResponseEntity.ok(eventoService.getEventoById(id));
    }
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> editEvento(@PathVariable Long id, @ModelAttribute EventoRequest request) throws EventNotExistException, IOException {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(request.getFechaHora());
        LocalDateTime localDateTime = offsetDateTime.toLocalDateTime();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());          
        byte[] imagenEventoBase64 = request.getImagenEvento().getBytes();
        byte[] imagenZonasBase64 = request.getImagenZonas().getBytes();
        eventoService.editEvento(
            id, 
            request.getNombre(), 
            request.getDescripcion(), 
            date, 
            request.getArtista(), 
            request.getLocacion(), 
            request.getEstado(), 
            request.getCategoria(), 
            request.getPdescuento(), 
            imagenEventoBase64, 
            imagenZonasBase64
        );
        return ResponseEntity.ok("Evento actualizado correctamente");
    }
    @DeleteMapping("/{id}") // only admin can delete events
    public ResponseEntity<String> deleteEvento(@PathVariable Long id) throws EventNotExistException {
        eventoService.deleteEvento(id);
        return ResponseEntity.ok("Evento eliminado correctamente");
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<Page<Evento>> getEventosPorCategoria(
            @PathVariable Long categoriaId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null) {
            return ResponseEntity.ok(eventoService.getEventosPorCategoria(categoriaId, PageRequest.of(0, 6)));
        }
        return ResponseEntity.ok(eventoService.getEventosPorCategoria(categoriaId, PageRequest.of(page, size)));
    }
}
