package com.uade.tpo.marketplace.entity.dto;
import lombok.Data;

@Data
public class EventoRequest {

    private String nombre;

    private String categoria;

    private Integer cant_entradas;
}

