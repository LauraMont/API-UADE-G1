package com.uade.tpo.marketplace.entity.dto;

import com.uade.tpo.marketplace.entity.Evento;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class ArtistaRequest {

    private String nombre;
    private String description;
    private String genero;
    private Evento evento;

}
