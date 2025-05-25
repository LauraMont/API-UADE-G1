package com.uade.tpo.marketplace.entity.dto;
import java.sql.Date;

import com.uade.tpo.marketplace.enums.EstadoEvento;
import lombok.Data;

@Data
public class EventoRequest {
    private String nombre;
    private String descripcion;
    private Date fechaHora;
    private String locacionId;
    private String artistaId;
    private EstadoEvento estado;
    private String categoriaId;
    private int stockEntradas;
}

