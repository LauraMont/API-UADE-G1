package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.entity.Artista;
import com.uade.tpo.marketplace.entity.Butaca;
import com.uade.tpo.marketplace.entity.Categoria;
// import com.uade.tpo.marketplace.controllers.EventoRequest;
import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.entity.Locacion;
import com.uade.tpo.marketplace.entity.Zona;
import com.uade.tpo.marketplace.enums.EstadoEvento;
import com.uade.tpo.marketplace.exceptions.ArtistaNotExistException;
import com.uade.tpo.marketplace.exceptions.EventDuplicateException;
import com.uade.tpo.marketplace.exceptions.EventNotExistException;
import com.uade.tpo.marketplace.exceptions.LocacionNotExistException;
import com.uade.tpo.marketplace.repository.ArtistaRepository;
import com.uade.tpo.marketplace.repository.ButacaRepository;
import com.uade.tpo.marketplace.repository.CategoriaRepository;
import com.uade.tpo.marketplace.repository.EntradaRepository;
import com.uade.tpo.marketplace.repository.EventoRepository;
import com.uade.tpo.marketplace.repository.LocacionRepository;
import com.uade.tpo.marketplace.repository.ZonasRepository;

import java.sql.Date;
import java.util.List;

import org.apache.logging.log4j.spi.LocationAwareLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class EventoServiceImpl implements EventoService {
    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private final CategoriaRepository categoriaRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private LocacionRepository locacionRepository;

    @Autowired
    private ButacaRepository butacaRepository;

    @Autowired
    private ZonasRepository zonasRepository;

    EventoServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Evento crearEvento(String nombre, String descripcion, Date fecha_hora, String artistaId, String locacionId, EstadoEvento estado, String categoriaId) throws EventDuplicateException, ArtistaNotExistException, LocacionNotExistException {
        Long artistaIdLong = Long.parseLong(artistaId);
        List<Evento> eventos = eventoRepository.findByNombre(nombre);
        Artista artista = artistaRepository.findByArtista_Id(artistaIdLong);
        Locacion locacion = locacionRepository.findBy_Id(Long.parseLong(locacionId));
        Categoria categoria = categoriaRepository.findBy_Id(Long.parseLong(categoriaId));
        if (artista == null) {
            throw new ArtistaNotExistException();
        }
        if(locacion == null) {
            throw new LocacionNotExistException();
        }
        if (eventos.isEmpty()) {
            Evento evento = new Evento(nombre, descripcion, fecha_hora, artista, locacion, estado, categoria, locacion.getCapacidad_total());
            return eventoRepository.save(evento);
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
    public void editEvento(Long eventoId, String nombre, String descripcion, Date fecha_hora, String artistaId, String locacionId ,EstadoEvento estado, String categoriaId, int cant_entradas) throws EventNotExistException {
        Long artistaIdLong = Long.parseLong(artistaId);
        Artista artista = artistaRepository.findByArtista_Id(artistaIdLong);
        Locacion locacion = locacionRepository.findBy_Id(Long.parseLong(locacionId));
        Categoria categoria = categoriaRepository.findBy_Id(Long.parseLong(categoriaId));
        if (!eventoRepository.existsById(eventoId)) {
            throw new EventNotExistException();
        }
        if(artista== null) {
            throw new EventNotExistException();
        }
        if(locacion== null) {
            throw new EventNotExistException();
        }
        eventoRepository.updateEvento(eventoId, nombre, descripcion, fecha_hora, artista, locacion, estado, categoria, cant_entradas);
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
        Long categoriaId = categoriaRepository.findByNombreIgnoreCase(categoria).get().getId();
        return eventoRepository.findByCategoriaId(categoriaId);
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