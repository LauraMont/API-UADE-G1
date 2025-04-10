package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public Evento crearEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override
    public List<Evento> obtenerTodos() {
        return eventoRepository.findAll();
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

    @Override
    public Evento actualizarEvento(Long id, Evento evento) {
        Evento existente = eventoRepository.findById(id).orElseThrow();
        evento.setId(id);
        return eventoRepository.save(evento);
    }

    @Override
    public void eliminarEvento(Long id) {
        eventoRepository.deleteById(id);
    }
}