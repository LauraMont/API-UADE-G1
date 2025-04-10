package com.uade.tpo.marketplace.service;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

// import com.uade.tpo.marketplace.controllers.EventoRequest;
import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.exceptions.EventDuplicateException;
import com.uade.tpo.marketplace.exceptions.EventNotExistException;

public interface EventoService {

    public Page<Evento> getEventos(PageRequest pageRequest);
    public Evento getEventoById(Long eventoId) throws EventNotExistException;
    public void editEvento(Long eventoId, String nombre, String descripcion, Date fecha_hora, String estado, String categoria, int cant_entradas) throws EventNotExistException;
    public void deleteEvento(Long eventoId) throws EventNotExistException;
    public Evento crearEvento(String nombre, String descripcion, Date fecha_hora, String estado, String categoria, int cant_entradas) throws EventDuplicateException;
}

