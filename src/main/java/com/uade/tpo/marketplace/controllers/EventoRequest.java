package com.uade.tpo.marketplace.controllers;
import lombok.Data;

@Data
public class EventoRequest {

    private String nombre;

    private String categoria;

    private Integer cant_entradas;
}

