package com.uade.tpo.marketplace.entity.dto;
import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.uade.tpo.marketplace.enums.EstadoEvento;

import lombok.Data;

@Data
public class EventoRequest {

    private String nombre;
    private String descripcion;
    private LocalDateTime fechaHora;
    private String artista;
    private String locacion;
    private EstadoEvento estado;
    private String categoria;
    private int pdescuento;
    private int stockEntradas;
    private MultipartFile imagenEvento;
    private MultipartFile imagenZonas;
}

