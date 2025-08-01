package com.uade.tpo.marketplace.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
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
    public void editEvento(Long eventoId, String nombre, String descripcion, Date fecha_hora, String artistaId, String locacionId, EstadoEvento estado, String categoriaId, int pdescuento, byte[] imagenEvento, byte[] imagenZonas) throws EventNotExistException, IOException;
    public void deleteEvento(Long eventoId) throws EventNotExistException;
    public Evento crearEvento(String nombre, String descripcion, Date fecha_hora, String artistaId, String locacionId, EstadoEvento estado, String categoriaId, int pDescuento, byte[] imagenEvento, byte[] imagenZonas) throws EventDuplicateException, ArtistaNotExistException, LocacionNotExistException, IOException;

    public List<Evento> buscarPorNombre(String nombre);

    public Page<Evento> buscarPorNombre(String nombre, PageRequest pageRequest);

    public List<Evento> buscarPorCategoria(String categoria);

    public List<Evento> buscarPorArtista(String artista);

    public List<Evento> buscarPorLocacion(Long locacionId);

    public Page<Evento> getEventosPorLocacion(Long locacionId, PageRequest pageRequest);

    public List<Evento> obtenerDisponibles();

    public int obtenerDescuentoPorEvento(Long eventoId);
    
    public Page<Evento> getEventosPorCategoria(Long categoriaId, PageRequest pageRequest);
}

