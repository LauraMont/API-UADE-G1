package com.uade.tpo.marketplace.entity.dto;

import lombok.Data;

@Data
public class ZonaRequest {
    private Long id;
    private float precio_base;
    private int cantidad_butacas;

    public ZonaRequest(float precio_base, int cantidad_butacas, Long id) {
        this.id = id;
        this.precio_base = precio_base;
        this.cantidad_butacas = cantidad_butacas;
    }
}
