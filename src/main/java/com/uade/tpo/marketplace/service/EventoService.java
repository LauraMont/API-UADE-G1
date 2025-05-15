package com.uade.tpo.marketplace.service;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.enums.EstadoEvento;
import com.uade.tpo.marketplace.exceptions.ArtistaNotExistException;
import com.uade.tpo.marketplace.exceptions.EventDuplicateException;
import com.uade.tpo.marketplace.exceptions.EventNotExistException;
import com.uade.tpo.marketplace.exceptions.LocacionNotExistException;

public interface EventoService {

    public Page<Evento> getEventos(PageRequest pageRequest);
    public Evento getEventoById(Long eventoId) throws EventNotExistException;
    public void editEvento(Long eventoId, String nombre, String descripcion, Date fecha_hora, String artistaId,String locacionId , EstadoEvento estado, String categoriaId, int cant_entradas) throws EventNotExistException;
    public void deleteEvento(Long eventoId) throws EventNotExistException;
    public Evento crearEvento(String nombre, String descripcion, Date fecha_hora, String artistaId, String locacionId, EstadoEvento estado, String categoriaId) throws EventDuplicateException, ArtistaNotExistException, LocacionNotExistException ;

    // Evento crearEvento(Evento evento);

    public List<Evento> buscarPorNombre(String nombre);

    public List<Evento> buscarPorCategoria(String categoria);

    public List<Evento> buscarPorArtista(String artista);

    public List<Evento> obtenerDisponibles();

    // Evento actualizarEvento(Long id, Evento evento);

    // void eliminarEvento(Long id);
}

