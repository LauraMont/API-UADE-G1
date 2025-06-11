package com.uade.tpo.marketplace.entity.dto;

import java.util.List;

import lombok.Data;

@Data
public class LocacionRequest {
    private String nombre;
    private String direccion;
    private int capacidad_total;
    private List<ZonaRequest> zonas;

    public LocacionRequest(String nombre, String direccion, int capacidad_total) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad_total = 0;
    }
}

