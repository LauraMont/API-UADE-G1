package com.uade.tpo.marketplace.controllers;
import lombok.Data;

@Data
public class EventoRequest {

    private String nombre;
    private String descripcion;
    private String fecha_hora;
    private String estado;
    private String categoria;
    private int cant_entradas;
}

