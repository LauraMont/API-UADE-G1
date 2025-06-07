package com.uade.tpo.marketplace.entity.dto;

import lombok.Data;

@Data
public class ZonaResponse {
    private Long id;
    private String nombre;
    private float precioBase;
    private float precioFinal;
    private int cantidadButacas;
    private int descuentoAplicado;

    public ZonaResponse(Long id, String nombre, float precioBase, float precioFinal, int cantidadButacas, int descuentoAplicado) {
        this.id = id;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.precioFinal = precioFinal;
        this.cantidadButacas = cantidadButacas;
        this.descuentoAplicado = descuentoAplicado;
    }
}
