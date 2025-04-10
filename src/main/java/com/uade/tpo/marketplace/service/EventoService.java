package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.entity.Evento;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventoService {
    Evento crearEvento(Evento evento);

    List<Evento> obtenerTodos();

    List<Evento> buscarPorNombre(String nombre);

    List<Evento> buscarPorCategoria(String categoria);

    List<Evento> buscarPorArtista(String artista);

    List<Evento> obtenerDisponibles();

    Evento actualizarEvento(Long id, Evento evento);

    void eliminarEvento(Long id);
}

