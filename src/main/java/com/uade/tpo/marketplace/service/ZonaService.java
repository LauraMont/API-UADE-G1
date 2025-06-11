package com.uade.tpo.marketplace.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Evento;
import com.uade.tpo.marketplace.entity.Zona;
import com.uade.tpo.marketplace.entity.dto.ZonaResponse;
import com.uade.tpo.marketplace.exceptions.EventNotExistException;
import com.uade.tpo.marketplace.exceptions.LocacionNotExistException;
import com.uade.tpo.marketplace.repository.EventoRepository;
import com.uade.tpo.marketplace.repository.LocacionRepository;
import com.uade.tpo.marketplace.repository.ZonasRepository;

@Service
public class ZonaService {

    @Autowired
    private ZonasRepository zonaRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public List<ZonaResponse> obtenerZonasConPrecioFinal(Long locacionId, Long eventoId)
            throws LocacionNotExistException, EventNotExistException {

        // Obtener las zonas
        List<Zona> zonas = zonaRepository.findByLocacionId(locacionId);

        int porcentajeDescuento;

        if (eventoId != null) {
            Evento evento = eventoRepository.findBy_Id(eventoId);
            if (evento == null) {
                throw new EventNotExistException();
            }

            // ✅ Validar que el evento pertenezca a la misma locación
            Long locacionDelEvento = evento.getLocacion().getId();
            if (!locacionDelEvento.equals(locacionId)) {
                throw new IllegalArgumentException("El evento no pertenece a la locación solicitada.");
            }

            porcentajeDescuento = evento.getPdescuento();
        }else {
            // Si no se proporciona un evento, se asume que no hay descuento
            porcentajeDescuento = 0;
        }

        // Mapear zonas con descuento aplicado
        return zonas.stream().map(zona -> {
            float precioBase = zona.getPrecio_base();
            float precioFinal = precioBase - (precioBase * (porcentajeDescuento / 100f));

            return new ZonaResponse(
                    zona.getId(),
                    zona.getNombre(),
                    precioBase,
                    precioFinal,
                    zona.getCantidad_butacas(),
                    porcentajeDescuento
            );
        }).collect(Collectors.toList());
    }
}
