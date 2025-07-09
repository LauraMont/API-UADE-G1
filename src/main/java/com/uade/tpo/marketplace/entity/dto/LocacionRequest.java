package com.uade.tpo.marketplace.entity.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LocacionRequest {
    private Long id;
    private String nombre;
    private String direccion;
    private int capacidad_total;
    private List<ZonaRequest> zonas;

    public LocacionRequest(String nombre, String direccion, int capacidad_total) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad_total = capacidad_total;
    }

    public LocacionRequest(Long id, String nombre, String direccion, int capacidad_total, List<ZonaRequest> zonas) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad_total = capacidad_total;
        this.zonas = zonas;
    }
}

