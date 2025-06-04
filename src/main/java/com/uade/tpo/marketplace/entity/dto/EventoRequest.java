package com.uade.tpo.marketplace.entity.dto;
import java.sql.Date;
import java.util.List;

import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.enums.EstadoEvento;

import lombok.Data;

@Data
public class EventoRequest {

    private String nombre;
    private String descripcion;
    private Date fechaHora;
    private String artista;
    private String locacion;
    private EstadoEvento estado;
    private String categoria;
    private int stockEntradas;

}

