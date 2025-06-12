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

    public Evento crearEvento(
        String nombre,
        String descripcion,
        Date fecha_hora,
        String artistaId,
        String locacionId,
        EstadoEvento estado,
        String categoriaId,
        int pDescuento,
        String imagenEvento,
        String imagenZonas
    ) throws EventDuplicateException, ArtistaNotExistException, LocacionNotExistException {
        Long artistaIdLong = Long.parseLong(artistaId);
        Long locacionIdLong = Long.parseLong(locacionId);
        Long categoriaIdLong = Long.parseLong(categoriaId);

        // Validar existencia
        Artista artista = artistaRepository.findByArtista_Id(artistaIdLong);
        if (artista == null) throw new ArtistaNotExistException();

        Locacion locacion = locacionRepository.findBy_Id(locacionIdLong);
        if (locacion == null) throw new LocacionNotExistException();

        Categoria categoria = categoriaRepository.findBy_Id(categoriaIdLong);
        if (categoria == null) throw new RuntimeException("Categoría no encontrada");

        // Validar que no exista otro evento en la misma locación y fecha
        List<Evento> eventosMismaFechaYLocacion = eventoRepository
            .findByLocacionIdAndFecha(locacionIdLong, fecha_hora);

        if (!eventosMismaFechaYLocacion.isEmpty()) {
            throw new EventDuplicateException();
        }

        // Crear evento
        Evento evento = new Evento(
            nombre,
            descripcion,
            fecha_hora,
            artista,
            locacion,
            estado,
            categoria,
            locacion.getCapacidad_total(),
            pDescuento,
            imagenEvento,
            imagenZonas
        );

        return eventoRepository.save(evento);
    }

    @Override
    public Page<Evento> getEventos(PageRequest pageRequest) {
        // If no page size is specified, use 6 as default
        if (pageRequest.getPageSize() == 20) { // 20 is Spring's default
            pageRequest = PageRequest.of(pageRequest.getPageNumber(), 6, pageRequest.getSort());
        }
        return eventoRepository.findAll(pageRequest);
    }

    @Override
    public Evento getEventoById(Long eventoId) throws EventNotExistException {
        return eventoRepository.findById(eventoId).orElseThrow(() -> new EventNotExistException());
    }

    @Override
    public void editEvento(Long eventoId, String nombre, String descripcion, Date fecha_hora, String artistaId, String locacionId, EstadoEvento estado, String categoriaId, int pdescuento, String imagenEvento, String imagenZonas) throws EventNotExistException {
        Long artistaIdLong = Long.parseLong(artistaId);
        Long locacionIdLong = Long.parseLong(locacionId);
        Long categoriaIdLong = Long.parseLong(categoriaId);

        Artista artista = artistaRepository.findByArtista_Id(artistaIdLong);
        if (artista == null) throw new EventNotExistException();

        Locacion locacion = locacionRepository.findBy_Id(locacionIdLong);
        if (locacion == null) throw new EventNotExistException();

        Categoria categoria = categoriaRepository.findBy_Id(categoriaIdLong);
        if (categoria == null) throw new EventNotExistException();

        if (!eventoRepository.existsById(eventoId)) {
            throw new EventNotExistException();
        }

        eventoRepository.updateEvento(
            eventoId, 
            nombre, 
            descripcion, 
            fecha_hora, 
            artista, 
            locacion, 
            estado, 
            categoria, 
            locacion.getCapacidad_total(), 
            imagenEvento, 
            imagenZonas
        );
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
    public Page<Evento> buscarPorNombre(String nombre, PageRequest pageRequest) {
        // If no page size is specified, use 6 as default
        if (pageRequest.getPageSize() == 20) { // 20 is Spring's default
            pageRequest = PageRequest.of(pageRequest.getPageNumber(), 6, pageRequest.getSort());
        }
        return eventoRepository.findByNombreContainingIgnoreCase(nombre, pageRequest);
    }

    @Override
    public List<Evento> buscarPorCategoria(String categoria) {
        Long categoriaId = categoriaRepository.findByNombreIgnoreCase(categoria).get().getId();
        return eventoRepository.findByCategoriaId(categoriaId);
    }

    @Override
    public List<Evento> buscarPorArtista(String artista) {
        // Primero buscamos el artista por nombre
        Artista artistaEntity = artistaRepository.findByNombreIgnoreCase(artista)
            .orElseThrow(() -> new RuntimeException("Artista no encontrado"));
        // Luego buscamos los eventos por el ID del artista
        return eventoRepository.findByArtista_Id(artistaEntity.getId());
    }

    @Override
    public List<Evento> obtenerDisponibles() {
        return eventoRepository.findByStockEntradasGreaterThan(0);
    }

    @Override
    public int obtenerDescuentoPorEvento(Long eventoId) {
        Evento evento = eventoRepository.findBy_Id(eventoId);
        return evento.getPdescuento();
    }

    @Override
    public Page<Evento> getEventosPorCategoria(Long categoriaId, PageRequest pageRequest) {
        // If no page size is specified, use 6 as default
        if (pageRequest.getPageSize() == 20) { // 20 is Spring's default
            pageRequest = PageRequest.of(pageRequest.getPageNumber(), 6, pageRequest.getSort());
        }
        return eventoRepository.findByCategoriaId(categoriaId, pageRequest);
    }

    @Override
    public List<Evento> buscarPorLocacion(Long locacionId) {
        return eventoRepository.findByLocacionId(locacionId);
    }

    @Override
    public Page<Evento> getEventosPorLocacion(Long locacionId, PageRequest pageRequest) {
        // If no page size is specified, use 6 as default
        if (pageRequest.getPageSize() == 20) { // 20 is Spring's default
            pageRequest = PageRequest.of(pageRequest.getPageNumber(), 6, pageRequest.getSort());
        }
        return eventoRepository.findByLocacionId(locacionId, pageRequest);
    }

}