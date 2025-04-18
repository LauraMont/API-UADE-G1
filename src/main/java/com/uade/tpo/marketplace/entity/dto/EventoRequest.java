package com.uade.tpo.marketplace.entity.dto;
import java.sql.Date;

import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.enums.EstadoEvento;

import lombok.Data;

@Data
public class EventoRequest {

    private String nombre;
    private String descripcion;
    private Date fechaHora;
    private String artista;
    private EstadoEvento estado;
    private Categoria categoria;
    private int cantEntradas;
    private int stockEntradas;
}

