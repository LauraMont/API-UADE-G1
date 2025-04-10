package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.entity.Categoria;
// import com.uade.tpo.marketplace.controllers.EventoRequest;
import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.enums.EstadoEvento;
import com.uade.tpo.marketplace.exceptions.EventDuplicateException;
import com.uade.tpo.marketplace.exceptions.EventNotExistException;
import com.uade.tpo.marketplace.repository.EventoRepository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Evento crearEvento(String nombre, String descripcion, Date fecha_hora, String artista, EstadoEvento estado, Categoria categoria, int cant_entradas) throws EventDuplicateException {
        // Verificar si el evento ya existe
        List<Evento> eventos = eventoRepository.findByNombre(nombre);
        if (eventos.isEmpty()) {
            return eventoRepository.save(new Evento(nombre, descripcion, fecha_hora, artista, estado, categoria, cant_entradas));
        }
        throw new EventDuplicateException();

    }

    @Override
    public Page<Evento> getEventos(PageRequest pageRequest) {
        return eventoRepository.findAll(pageRequest);
    }

    @Override
    public Evento getEventoById(Long eventoId) throws EventNotExistException {
        return eventoRepository.findById(eventoId).orElseThrow(() -> new EventNotExistException());
    }

    @Override
    public void editEvento(Long eventoId, String nombre, String descripcion, Date fecha_hora, String artista, EstadoEvento estado, Categoria categoria, int cant_entradas) throws EventNotExistException {
        if (!eventoRepository.existsById(eventoId)) {
            throw new EventNotExistException();
        }
        eventoRepository.updateEvento(eventoId, nombre, descripcion, fecha_hora, artista, estado, categoria, cant_entradas);
    }

    @Override
    public void deleteEvento(Long eventoId) throws EventNotExistException {
        if (!eventoRepository.existsById(eventoId)) {
            throw new EventNotExistException();
        }
        eventoRepository.deleteById(eventoId);
    }

    @Override
    public List<Evento> buscarPorNombre(String nombre) {
        return eventoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Evento> buscarPorCategoria(String categoria) {
        return eventoRepository.findByCategoriaNombreContainingIgnoreCase(categoria);
    }

    @Override
    public List<Evento> buscarPorArtista(String artista) {
        return eventoRepository.findByArtistaContainingIgnoreCase(artista);
    }

    @Override
    public List<Evento> obtenerDisponibles() {
        return eventoRepository.findByStockEntradasGreaterThan(0);
    }

}